package com.hiservice.mobile.data.retrofit.gson

import com.google.gson.annotations.SerializedName

data class ResponseItemData(

	@field:SerializedName("data")
	val data: List<DataItemsInventory>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItemsInventory(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id_barang")
	val idBarang: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("qty")
	val qty: String? = null,

	@field:SerializedName("id_bengkel")
	val idBengkel: String? = null,

	@field:SerializedName("nama_barang")
	val namaBarang: String? = null
)
