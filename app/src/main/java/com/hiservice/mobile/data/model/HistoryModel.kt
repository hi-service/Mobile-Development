package com.hiservice.mobile.data.model

data class HistoryModel (
    val id: Long,
    val namaService: String,
    val namaBengkel: String,
    val tanggalService: String,
    val jamService: String,
    val descService: String,
    val kmService: String,
    val kmNext: String,
    val statusOrder: String,
    val totalHarga: Int
)
