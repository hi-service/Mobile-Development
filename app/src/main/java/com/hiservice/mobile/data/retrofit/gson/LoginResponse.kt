package com.hiservice.mobile.data.retrofit.gson

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("auth_token")
	val authToken: String? = null
)
