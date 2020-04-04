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

package com.ambition.query.controller;

import com.ambition.query.comm.Result;
import com.ambition.query.domain.UserInfo;
import com.ambition.query.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wpl
 */
@RestController
@RequestMapping("/user/info")
@Api(value = "用户信息", description = "用户信息", tags = {"user info manager controller"})
public class UserInfoController {

  @Autowired
  UserInfoService userInfoService;

  @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ApiOperation(value = "登入", notes = "登入", response = Result.class)
  public ResponseEntity<?> login(
      @ApiParam(value = "账号") @RequestParam(value = "userName") String  userName,
      @ApiParam(value = "密码") @RequestParam(value = "userPassword") String  userPassword
  ) {
    //用户名和密码匹配
    Result result = userInfoService.getUserInfoByUserName(userName);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @ApiOperation(value = "通过id获取用户",httpMethod = "GET")
  @ApiImplicitParams(value = {})
  @GetMapping(value = "/user/get_id/{id}")
  public UserInfo getUserById(@PathVariable("id") String id) {
    try {
//      return userInfoService.getUserById(id);
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

}
