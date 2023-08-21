package com.htcy.wanandroid.data.bean

import com.htcy.rxhttp.request.base.BaseBean

/*
* {
    "data": {
        "admin": false,
        "chapterTops": [],
        "coinCount": 10,
        "collectIds": [],
        "email": "",
        "icon": "",
        "id": 152337,
        "nickname": "htcy",
        "password": "",
        "publicName": "htcy",
        "token": "",
        "type": 0,
        "username": "htcy"
    },
    "errorCode": 0,
    "errorMsg": ""
    *
    * {
    "data": null,
    "errorCode": -1,
    "errorMsg": "账号密码不匹配！"
}
}*/
class LoginBean(val email: String, val icon: String, val id: Int, val password: String, val admin: Boolean, val publicName: String, val token: String, val username: String): BaseBean()