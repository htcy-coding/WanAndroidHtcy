package com.htcy.wanandroid.data.api;

import com.htcy.arti.data.response.manager.DataResult;
import com.htcy.wanandroid.data.bean.LoginBean;
import com.htcy.wanandroid.data.bean.RegisterBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {


    /**
     * 登录
     * 方法： POST
     * 参数：
     * username，password
     * 登录后会在cookie中返回账号密码，只要在客户端做cookie持久化存储即可自动登录验证。
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<DataResult<LoginBean>> login(@Field("username") String username,
                                            @Field("password") String password);


    /**
     * 注册
     * 方法： POST
     * 参数：
     * username，password,repassword
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<DataResult<RegisterBean>> register(@Field("username") String username,
                                                   @Field("password") String password,
                                                   @Field("repassword") String repassword);

}
