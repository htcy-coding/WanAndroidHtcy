package com.htcy.rxhttp.core.utils;


import androidx.annotation.NonNull;

/**
 * 描述：检查BaseUrl是否以"/"结尾
 *
 * @author Cuizhen
 * @date 2018/10/13
 */
public class BaseUrlUtils {

    public static String checkBaseUrl(@NonNull String url) {
        if (url.endsWith("/")) {
            return url;
        } else {
            return url + "/";
        }
    }
}
