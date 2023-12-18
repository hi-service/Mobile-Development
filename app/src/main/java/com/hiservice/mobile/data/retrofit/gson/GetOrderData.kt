package com.hiservice.mobile.data.retrofit.gson

import com.google.gson.annotations.SerializedName

data class GetOrderData(

	@field:SerializedName("order_data")
	val orderData: OrderData? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class OrderData(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("nama_bengkel")
	val namaBengkel: String? = null,

	@field:SerializedName("lng")
	val lng: String? = null,

	@field:SerializedName("waktu")
	val waktu: String? = null,

	@field:SerializedName("nohp")
	val nohp: String? = null,

	@field:SerializedName("id_location")
	val idLocation: String? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("order_id")
	val orderId: String? = null,

	@field:SerializedName("lat")
	val lat: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
