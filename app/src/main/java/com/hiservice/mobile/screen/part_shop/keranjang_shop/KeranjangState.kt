package com.hiservice.mobile.screen.part_shop.keranjang_shop

import com.hiservice.mobile.data.model.PartShopModelWrapper

data class KeranjangState (
    val orderSparePart: List<PartShopModelWrapper>,
    val totalHarga: Int
)