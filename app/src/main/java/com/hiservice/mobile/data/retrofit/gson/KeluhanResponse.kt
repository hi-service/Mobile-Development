package com.hiservice.mobile.data.retrofit.gson

import com.google.gson.annotations.SerializedName

data class KeluhanResponse(

	@field:SerializedName("data")
	val data: List<DataKeluhanItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataKeluhanItem(

	@field:SerializedName("text_keluhan")
	val textKeluhan: String? = null
)
