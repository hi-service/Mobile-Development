package com.hiservice.mobile.data.model

data class HistoryModel (
    val id: Long,
    val namaService: String,
    val namaBengkel: String,
    val tanggalService: String,
    val jamService: String,
    val totalHarga: Int
)
