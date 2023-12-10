package com.hiservice.mobile.data.retrofit.gson

import com.google.gson.annotations.SerializedName

data class PostResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("salary")
	val salary: String? = null,

	@field:SerializedName("age")
	val age: String? = null
)
