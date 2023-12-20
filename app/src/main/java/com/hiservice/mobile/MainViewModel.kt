package com.hiservice.mobile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hiservice.mobile.data.model.SharedData
import com.hiservice.mobile.data.model.ShopSharedData

class MainViewModel : ViewModel() {
    private val _sharedData = mutableStateOf<SharedData?>(null)
    val sharedData: State<SharedData?> get() = _sharedData

    private val _shopSharedData = mutableStateOf<ShopSharedData?>(null)
    val shopSharedData: State<ShopSharedData?> get() = _shopSharedData

    fun setShareData(sharedData: SharedData?){
        _sharedData.value = sharedData
    }
    fun setShopShareData(shopSharedData: ShopSharedData?){
        _shopSharedData.value = shopSharedData
    }
}