package com.hiservice.mobile.data.retrofit.gson

import com.google.gson.annotations.SerializedName

data class OrderStatusPost(

	@field:SerializedName("order_status")
	val orderStatus: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
