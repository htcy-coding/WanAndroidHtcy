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
package com.htcy.wanandroid.data.repository

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kunminx.architecture.data.response.DataResult
import com.kunminx.architecture.data.response.ResponseStatus
import com.kunminx.architecture.data.response.ResultSource
import com.kunminx.architecture.domain.request.AsyncTask
import com.kunminx.architecture.utils.Utils
import com.kunminx.puremusic.R
import com.kunminx.puremusic.data.api.APIs
import com.kunminx.puremusic.data.api.AccountService
import com.kunminx.puremusic.data.bean.LibraryInfo
import com.kunminx.puremusic.data.bean.TestAlbum
import com.kunminx.puremusic.data.bean.User
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

/**
 * Create by KunMinX at 19/10/29
 */
class DataRepository private constructor() {
    private var retrofit: Retrofit? = null


    companion object {
        val instance: DataRepository = DataRepository()
    }

    init {
        val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(8, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl("")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}