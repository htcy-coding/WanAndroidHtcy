package com.htcy.rxhttp.request.base;


import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.htcy.rxhttp.request.utils.JsonFormatUtils;

import java.io.Serializable;


/**
 * 描述：网络请求的实体类基类
 *
 * @author Cuizhen
 * @date 2018/9/9
 */
public class BaseBean implements Serializable {

    @NonNull
    public String toJson() {
        return new Gson().toJson(this);
    }

    @NonNull
    public String toFormatJson() {
        return JsonFormatUtils.format(toJson());
    }
}