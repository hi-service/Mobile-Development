package com.hiservice.mobile.data.retrofit.gson

import com.google.gson.annotations.SerializedName

data class OrderResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
