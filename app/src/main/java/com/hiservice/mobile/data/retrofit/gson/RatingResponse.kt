package com.hiservice.mobile.data.retrofit.gson

import com.google.gson.annotations.SerializedName

data class RatingResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("rating_status")
	val message: String? = null
)
