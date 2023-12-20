package com.hiservice.mobile.data.model

data class SharedData(
    val deskripsiMasalah : String,
    val noHp : String,
    val lat : Double,
    val lng : Double,
    val alamat : String,
)

data class ShopSharedData(
    val lat : Double,
    val lng : Double,
)
