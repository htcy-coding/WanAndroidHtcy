package com.htcy.wanandroid.data.bean

import com.htcy.rxhttp.request.base.BaseBean

/*{
    "data": {
        "admin": false,
        "chapterTops": [],
        "coinCount": 0,
        "collectIds": [],
        "email": "",
        "icon": "",
        "id": 152338,
        "nickname": "htcy1",
        "password": "",
        "publicName": "htcy1",
        "token": "",
        "type": 0,
        "username": "htcy1"
    },
    "errorCode": 0,
    "errorMsg": ""
}
*
* */
class RegisterBean(val email: String, val icon: String, val id: Int, val password: String, val admin: Boolean, val publicName: String, val token: String, val username: String): BaseBean() {
}