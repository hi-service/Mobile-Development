package com.hiservice.mobile.data.model

data class StatusOrderModel(
    val statusOrder: String,
    val idOrder: String,
    val time: String,
    val image: Int,
    val text: String,
    val subText: String,
    val isButton: Boolean,
)