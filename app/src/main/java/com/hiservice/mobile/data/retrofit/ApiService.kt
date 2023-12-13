package com.hiservice.mobile.data.retrofit

import com.hiservice.mobile.data.retrofit.gson.LoginResponse
import com.hiservice.mobile.data.retrofit.gson.PostResponse
import com.hiservice.mobile.data.retrofit.gson.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun loginRequest(
        @Field("user_id") user_id: String,
    ): LoginResponse
    @FormUrlEncoded
    @POST("register")
    suspend fun registerRequest(
        @Field("user_id") user_id: String,
        @Field("user_name") user_name: String,
        ): LoginResponse


}