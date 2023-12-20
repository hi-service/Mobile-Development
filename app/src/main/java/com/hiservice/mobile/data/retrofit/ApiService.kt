package com.hiservice.mobile.data.retrofit

import com.hiservice.mobile.data.retrofit.gson.ChatOrderResponse
import com.hiservice.mobile.data.retrofit.gson.DetailBengkel
import com.hiservice.mobile.data.retrofit.gson.GetHistoryService
import com.hiservice.mobile.data.retrofit.gson.GetOrderData
import com.hiservice.mobile.data.retrofit.gson.GetUserResponse
import com.hiservice.mobile.data.retrofit.gson.KeluhanResponse
import com.hiservice.mobile.data.retrofit.gson.ListBengkel
import com.hiservice.mobile.data.retrofit.gson.LoginResponse
import com.hiservice.mobile.data.retrofit.gson.OrderResponse
import com.hiservice.mobile.data.retrofit.gson.OrderStatusPost
import com.hiservice.mobile.data.retrofit.gson.RatingResponse
import com.hiservice.mobile.data.retrofit.gson.RegisterResponse
import com.hiservice.mobile.data.retrofit.gson.ResponseDataML
import com.hiservice.mobile.data.retrofit.gson.ResponseItemData
import com.hiservice.mobile.data.retrofit.gson.ResponseShopBengkel
import com.hiservice.mobile.data.retrofit.gson.SendChatResponse
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
    @FormUrlEncoded
    @POST("setOrderStatusDone")
    suspend fun setOrderStatusDone(
        @Field("orderstatus") status: String,
    ): OrderStatusPost
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

    @FormUrlEncoded
    @POST("setOrderRating")
    suspend fun setRating(
        @Field("rating") rating: Int,
        @Field("id_bengkel") id_bengkel: String,
        @Field("statement") statement: String
    ): RatingResponse
    @GET("getOrderChat/{orderId}")
    suspend fun getChat(
        @Path("orderId") orderId : Int
    ): ChatOrderResponse

    @FormUrlEncoded
    @POST("setOrderChat")
    suspend fun sendMessage(
        @Field("id_order") id_order: Int,
        @Field("message") message: String,
    ): SendChatResponse

    @GET("getOrderHistory")
    suspend fun getOrderHistory(
    ): GetHistoryService

    @GET("getBengkelShop")
    suspend fun getBengkelShop(@Query("lat") lat: Double,@Query("lng") lng: Double
    ): ResponseShopBengkel

    @GET("getItemsData/{bengkelID}")
    suspend fun getItemsData(
        @Path("bengkelID") bengkelId : Int
    ): ResponseItemData

    @FormUrlEncoded
    @POST("/")
    suspend fun getAIGejala(
        @Field("gejala") message: String,
    ): ResponseDataML
}