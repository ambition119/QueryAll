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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wpl
 */
@Data
@ApiModel
public class ResultMeta {

  @ApiModelProperty(value = "错误码", required = true)
  private int code;
  @ApiModelProperty(value = "描述", required = true)
  private String msg;

  public ResultMeta() {}

  public ResultMeta(ErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.msg = errorCode.getMsg();
  }

  void setMsg(String msg) {
    this.msg = this.msg + " : " + msg;
  }
}
