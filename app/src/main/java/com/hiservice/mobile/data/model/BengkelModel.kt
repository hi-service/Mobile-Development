package com.hiservice.mobile.data.model

data class BengkelModel (
    val id: String,
    val namaBengkel: String,
    val rating: Double,
    val jenisBengkel: String,
    val descBengkel: String,
    val ownerBengkel: String,
    val alamatBengkel: String,
    val jamBuka: String
)
data class ListBengkel(
    val id_bengkel: String,
    val namaBengkel: String,
    val rating: Double,
    val lat: Double,
    val lng: Double,
    val jenisBengkel: String
        )

data class MapModel(
    val namaBengkel: String,
    val lat: Double,
    val lng : Double,
)