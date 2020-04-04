/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ambition.query.comm;

/**
 * @Author: wpl
 */
public enum ErrorCode {
  SUCCESS(1000, "success"),
  FAIL(1001, "fail"),

  FAIL_INSERT(1011, "fail insert"),
  FAIL_UPDATE(1012, "fail update"),
  FAIL_SELECT(1013, "fail select"),
  FAIL_DEL(1014, "fail delete"),

  FAIL_UPLOAD(1015, "fail upload"),
  FAIL_EXPORT(1016, "fail export"),

  FAIL_RUN_TASK(1021, "fail run task"),
  ;

  private int code;
  private String msg;

  ErrorCode(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }
}
