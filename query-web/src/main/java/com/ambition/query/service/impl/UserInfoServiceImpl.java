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

package com.ambition.query.service.impl;

import com.ambition.query.comm.ErrorCode;
import com.ambition.query.comm.Result;
import com.ambition.query.dao.UserInfoMapper;
import com.ambition.query.domain.UserInfo;
import com.ambition.query.service.UserInfoService;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: wpl
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
  private static final Logger LOG = LoggerFactory.getLogger(UserInfoServiceImpl.class);

  @Resource
  private UserInfoMapper userInfoMapper;

  @Override
  public Result getUserInfoByUserName(String userName) {
    if (StringUtils.isEmpty(userName)) {
      String message = "参数错误，注意非常空字段 ";
      LOG.error(message);
      return Result.fail(ErrorCode.FAIL_SELECT).withErrorMsg(message);
    }
    try {
      UserInfo userInfo = userInfoMapper.getUserInfoByUserName(userName);
      return Result.success().withResponse(userInfo);
    } catch (Exception e) {
      LOG.error("指定账号查询失败 " + userName, e);
      return Result.fail(ErrorCode.FAIL_SELECT).withErrorMsg(e.getMessage());
    }
  }
}
