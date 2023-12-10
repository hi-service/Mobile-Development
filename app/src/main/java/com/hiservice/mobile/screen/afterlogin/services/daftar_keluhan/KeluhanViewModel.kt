package com.hiservice.mobile.screen.afterlogin.services.daftar_keluhan

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.hiservice.mobile.data.fake_data.KeluhanFakeData.listKeluhan
import com.hiservice.mobile.data.model.Keluhan

class KeluhanViewModel : ViewModel() {
    private val _itemsState = mutableStateListOf<Keluhan>()
    val itemsState: List<Keluhan> = _itemsState

    init {
        _itemsState.addAll(listKeluhan)
    }
    fun changeData(index: Int) {
            if(_itemsState[index].isChecklist){
                _itemsState[index] = _itemsState[index].copy(isChecklist = false)
            }else{
                _itemsState[index] = _itemsState[index].copy(isChecklist = true)
            }

    }
}