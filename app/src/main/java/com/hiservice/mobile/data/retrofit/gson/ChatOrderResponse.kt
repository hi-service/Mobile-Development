package com.hiservice.mobile.data.retrofit.gson

import com.google.gson.annotations.SerializedName

data class ChatOrderResponse(

	@field:SerializedName("chat")
	val chat: List<ChatItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ChatItem(

	@field:SerializedName("id_chat")
	val id_chat: String? = null,

	@field:SerializedName("sender")
	val sender: String? = null,

	@field:SerializedName("waktu")
	val waktu: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("order_id")
	val orderId: String? = null
)
