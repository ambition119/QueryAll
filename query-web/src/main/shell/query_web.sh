#!/bin/sh

    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
      http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.


# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

COMMAND="$1"

# Get standard environment variables
PRG_DIR=`dirname "$PRG"`
QUERY_WEB_HOME=`cd "$PRG_DIR/.." >/dev/null; pwd`
export QUERY_WEB_HOME

QUERY_WEB_CONF_DIR="$QUERY_WEB_HOME/config"
QUERY_WEB_LIB_DIR="$QUERY_WEB_HOME/lib"
export QUERY_WEB_LIB_DIR

LIB_JARS=`ls $QUERY_WEB_LIB_DIR | grep -E '.jar$' | awk '{print "'$QUERY_WEB_LIB_DIR'/"$0}' | tr "\n" ":"`

PRO_NAME=${project.artifactId}
PIDFILE="$QUERY_WEB_HOME/$(basename $PRO_NAME).pid"
PID=0
if [[ -f $PIDFILE ]]; then
  PID=`cat $PIDFILE`
fi

JAVA_OPTS=""
JAVA_MEM_OPTS=""
JAVA_SPRING_OPTS=" --spring.profiles.active=${env}"

QUERY_WEB_MAIN_CLASS=${start-class}

running() {
  if [[ -z $1 || $1 == 0 ]]; then
    echo 0
    return
  fi
  if ps -p $1 > /dev/null; then
    echo 1
    return
  fi
  echo 0
  return
}

set_env() {
    if [ -r "$QUERY_WEB_HOME/bin/setenv.sh" ]; then
      source "$QUERY_WEB_HOME/bin/setenv.sh"
    else
      JAVA_MEM_OPTS=" -server -Xms4g -Xmx4g -XX:SurvivorRatio=2 -XX:+UseParallelGC "
    fi
}

start() {
  if [[ $(running $PID) != 0 ]]; then
    echo "$PRO_NAME is running"
    return
  fi
  echo "### starting $PRO_NAME `date '+%Y-%m-%d %H:%M:%S'` ###" >> /dev/null 2>&1 &
  set_env
  START_CMD="$JAVA_HOME/bin/java $JAVA_OPTS $JAVA_MEM_OPTS -classpath $QUERY_WEB_CONF_DIR:$LIB_JARS $QUERY_WEB_MAIN_CLASS $JAVA_SPRING_OPTS"
  print_env
  nohup $START_CMD >> $QUERY_WEB_HOME/server.log 2>&1 &
  if [[ $(running $!) == 0 ]]; then
    echo "failed to start $PRO_NAME"
    exit 1
  fi
  PID=$!
  echo $! > $PIDFILE
#  echo "new pid $!"
}

stop() {
  if [[ $(running $PID) == 0 ]]; then
    echo "no $PRO_NAME is running"
    return
  fi
  echo "stopping $PID of $PRO_NAME ..."
  kill $PID
}

restart() {
  stop
  start
}

print_env() {
  echo "JRE_HOME:               $JAVA_HOME"
  echo "ENV:                    ${env}"
  echo "JAVA_OPTS:              $JAVA_OPTS"
  echo "JAVA_MEM_OPTS:          $JAVA_MEM_OPTS"
  echo "JAVA_SPRING_OPTS:       $JAVA_SPRING_OPTS"
  echo "START_CMD:              $START_CMD"
}

print_usage() {
  echo "Usage: query_web.sh (start|stop)"
}

case $COMMAND in

(start)
  start
  ;;

(stop)
  stop
  ;;

(*)
  print_usage
  exit 1
  ;;
esac