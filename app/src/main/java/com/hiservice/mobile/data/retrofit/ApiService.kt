package com.hiservice.mobile.data.retrofit

import com.hiservice.mobile.data.retrofit.gson.DetailBengkel
import com.hiservice.mobile.data.retrofit.gson.GetOrderData
import com.hiservice.mobile.data.retrofit.gson.GetUserResponse
import com.hiservice.mobile.data.retrofit.gson.KeluhanResponse
import com.hiservice.mobile.data.retrofit.gson.ListBengkel
import com.hiservice.mobile.data.retrofit.gson.LoginResponse
import com.hiservice.mobile.data.retrofit.gson.OrderResponse
import com.hiservice.mobile.data.retrofit.gson.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun loginRequest(
        @Field("uid") user_id: String,
        @Field("fcm_token") fcm_token: String
    ): LoginResponse
    @FormUrlEncoded
    @POST("register")
    suspend fun registerRequest(
        @Field("uid") user_id: String,
        @Field("name") user_name: String,
        ): RegisterResponse
    @GET("locbengkel")
    suspend fun getLocBengkel(@Query("lat") lat: Double,@Query("lng") lng: Double
    ): ListBengkel

    @GET("getkeluhan")
    suspend fun getKeluhan(
    ): KeluhanResponse

    @GET("getUserData")
    suspend fun getUserData(
    ): GetUserResponse
    @GET("getOrderStatus")
    suspend fun getOrderStatus(
    ): GetOrderData
    @GET("getBengkelDesc/{id}")
    suspend fun getDesc(
        @Path("id") id : Int
    ): DetailBengkel
    @FormUrlEncoded
    @POST("setOrder")
    suspend fun setOrder(
        @Field("bengkel") bengkel: Int,
        @Field("desc") desc: String,
        @Field("nohp") nohp: String,
        @Field("lat") lat: Double,
        @Field("lng") lng: Double,
        @Field("address") address: String
    ): OrderResponse


}