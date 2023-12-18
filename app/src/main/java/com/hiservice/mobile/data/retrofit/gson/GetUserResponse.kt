package com.hiservice.mobile.data.retrofit.gson

import com.google.gson.annotations.SerializedName

data class GetUserResponse(

	@field:SerializedName("data")
	val data: DataUser? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataUser(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("last_order_id")
	val lastOrderId: String? = null,

	@field:SerializedName("status_order")
	val statusOrder: String? = null,

	@field:SerializedName("nohp")
	val nohp: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("status_buy_order")
	val statusBuyOrder: String? = null,

	@field:SerializedName("buy_last_id")
	val buyLastId: Any? = null
)
