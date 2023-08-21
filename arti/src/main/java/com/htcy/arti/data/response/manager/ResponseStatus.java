/*
 * Copyright 2018-present KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.htcy.arti.data.response.manager;

import static com.htcy.arti.data.response.manager.ResultSource.NETWORK;

/**
 * Create by KunMinX at 19/10/11
 *  "errorCode": -1,
 *     "errorMsg": "账号密码不匹配！"
 */
public class ResponseStatus {

  private String errorMsg = "";
  private boolean success = true;
  private int errorCode;

  private Enum<ResultSource> source = NETWORK;

  public ResponseStatus() {
  }

  public ResponseStatus(String responseCode, boolean success) {
    this.errorMsg = responseCode;
    this.success = success;
  }

  public ResponseStatus(String responseCode, boolean success, Enum<ResultSource> source) {
    this(responseCode, success);
    this.source = source;
  }

  public String getResponseCode() {
    return errorMsg;
  }

  public boolean isSuccess() {
    return success;
  }

  public Enum<ResultSource> getSource() {
    return source;
  }
}
