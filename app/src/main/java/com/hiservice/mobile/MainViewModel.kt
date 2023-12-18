package com.hiservice.mobile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hiservice.mobile.data.model.SharedData

class MainViewModel : ViewModel() {
    private val _sharedData = mutableStateOf<SharedData?>(null)
    val sharedData: State<SharedData?> get() = _sharedData

    fun setShareData(sharedData: SharedData){
        _sharedData.value = sharedData
    }
}