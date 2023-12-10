package com.hiservice.mobile.data.retrofit

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
    @POST("create")
    suspend fun registerUserId(
        @Field("name") name: String,
        @Field("age") age: String,
        @Field("salary") salary: String,

    ): PostResponse


}