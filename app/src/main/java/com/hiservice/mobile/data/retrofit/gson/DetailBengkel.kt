package com.hiservice.mobile.data.retrofit.gson

import com.google.gson.annotations.SerializedName

data class DetailBengkel(

	@field:SerializedName("data")
	val data: List<DeskripsiBengkelItem>? = null,

	@field:SerializedName("rating")
	val rating: Any? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DeskripsiBengkelItem(

	@field:SerializedName("jam_buka")
	val jamBuka: String? = null,

	@field:SerializedName("jam_tutup")
	val jamTutup: String? = null,

	@field:SerializedName("alamat_bengkel")
	val alamatBengkel: String? = null,

	@field:SerializedName("pemilik_bengkel")
	val pemilikBengkel: String? = null,

	@field:SerializedName("nama_bengkel")
	val namaBengkel: String? = null,

	@field:SerializedName("deskripsi_bengkel")
	val deskripsiBengkel: String? = null,

	@field:SerializedName("url_gambar")
	val urlGambar: String? = null,

	@field:SerializedName("jenis_bengkel")
	val jenisBengkel: String? = null
)
