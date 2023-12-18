package com.hiservice.mobile.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MM HH:mm", Locale.getDefault())
    val date: Date = inputFormat.parse(dateString) ?: return ""
    return outputFormat.format(date)
}