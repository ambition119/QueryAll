#!/bin/sh
PRG="$0"
PRGDIR=`dirname "$PRG"`
LABEL_HOME=`cd "$PRGDIR/.." >/dev/null; pwd`
EXECUTABLE=query_web.sh
exec "$PRGDIR"/"$EXECUTABLE" start "$@"
