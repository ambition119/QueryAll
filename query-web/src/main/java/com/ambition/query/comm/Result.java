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
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Author: wpl
 */
@Data
@ApiModel
public class Result<T> {

  @ApiModelProperty(value = "元数据", required = true)
  private ResultMeta meta;

  @ApiModelProperty(value = "返回结果")
  private T response;

  public Result() {}

  private Result(ErrorCode errorCode) {
    this.meta = new ResultMeta(errorCode);
  }

  public static <T> Result<T> success() {
    return new Result<>(ErrorCode.SUCCESS);
  }

  public static <T> Result<T> fail(ErrorCode errorCode) {
    return new Result<>(errorCode);
  }

  public static boolean isSuccess(Result result) {
    return result != null && result.meta != null && result.meta.getCode() == 0;
  }

  public Result<T> withErrorMsg(String msg) {
    this.meta.setMsg(msg);
    return this;
  }

  public Result<T> withResponse(T response) {
    this.response = response;
    return this;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
