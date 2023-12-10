package com.hiservice.mobile.data.retrofit.gson

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("user_name")
	val userName: String? = null
)
