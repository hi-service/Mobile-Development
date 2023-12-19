package com.hiservice.mobile.data.retrofit.gson

import com.google.gson.annotations.SerializedName

data class GetHistoryService(

	@field:SerializedName("data")
	val data: List<DataHistory>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataHistory(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("nama_bengkel")
	val namaBengkel: String? = null,

	@field:SerializedName("waktu")
	val waktu: String? = null,

	@field:SerializedName("nohp")
	val nohp: String? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("km_sesudah")
	val kmSesudah: Any? = null,

	@field:SerializedName("order_id")
	val orderId: String? = null,

	@field:SerializedName("km_sebelum")
	val kmSebelum: Any? = null,

	@field:SerializedName("status")
	val status: String? = null
)
