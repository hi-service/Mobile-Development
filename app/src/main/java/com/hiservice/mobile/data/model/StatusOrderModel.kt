package com.hiservice.mobile.data.model

import androidx.compose.ui.graphics.Color

data class StatusOrderModel(
    val statusOrder: String,
    val idOrder: String,
    val time: String,
    val image: Int,
    val text: String,
    val subText: String,
    val isButton: Boolean,
    val buttonClick: () -> Unit,
    val buttonText: String,
    val buttonColor: Color,
)