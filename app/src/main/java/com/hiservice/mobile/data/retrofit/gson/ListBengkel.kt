package com.hiservice.mobile.data.retrofit.gson

import com.google.gson.annotations.SerializedName

data class ListBengkel(

	@field:SerializedName("data")
	val data: List<DataListBengkel>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataListBengkel(

	@field:SerializedName("nama_bengkel")
	val namaBengkel: String? = null,

	@field:SerializedName("lng")
	val lng: Any? = null,

	@field:SerializedName("jenis_bengkel")
	val jenisBengkel: String? = null,

	@field:SerializedName("jarak")
	val jarak: Double? = null,

	@field:SerializedName("url_photo")
	val url_photo: String? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("lat")
	val lat: Any? = null
)
