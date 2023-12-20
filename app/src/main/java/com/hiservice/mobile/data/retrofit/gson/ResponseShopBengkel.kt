package com.hiservice.mobile.data.retrofit.gson

import com.google.gson.annotations.SerializedName

data class ResponseShopBengkel(

	@field:SerializedName("data_bengkel_shop")
	val dataBengkelShop: List<DataBengkelShopItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataBengkelShopItem(

	@field:SerializedName("total_barang")
	val totalBarang: Int? = null,

	@field:SerializedName("nama_bengkel")
	val namaBengkel: String? = null,

	@field:SerializedName("lng")
	val lng: Any? = null,

	@field:SerializedName("alamat_bengkel")
	val alamat_bengkel: String? = null,

	@field:SerializedName("url_photo")
	val urlPhoto: String? = null,


	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("lat")
	val lat: Any? = null,

	@field:SerializedName("jarak")
	val jarak: Double? = null
)
