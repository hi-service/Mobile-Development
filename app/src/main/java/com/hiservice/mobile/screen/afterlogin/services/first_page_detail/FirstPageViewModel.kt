package com.hiservice.mobile.screen.afterlogin.services.first_page_detail

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.model.AlertData
import com.hiservice.mobile.data.model.Keluhan
import com.hiservice.mobile.data.model.UserModel
import com.hiservice.mobile.data.retrofit.ApiConfig

import com.hiservice.mobile.data.retrofit.gson.DataListBengkel
import com.hiservice.mobile.data.retrofit.gson.ListBengkel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class FirstPageViewModel(val repository: Repository) : ViewModel() {
    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading

    private val _itemsStateFlow = MutableStateFlow<List<DataListBengkel>>(mutableListOf())
    val itemsStateFlow: StateFlow<List<DataListBengkel>> get() = _itemsStateFlow

    private val _deskripsi = mutableStateOf("")
    val deskripsi: State<String> get() = _deskripsi
    fun deskripsiText(newText: String) {
        _deskripsi.value = newText
    }
    private val _noHp = mutableStateOf("")
    val noHp: State<String> get() = _noHp
    fun noHpText(newText: String) {
        _noHp.value = newText
    }
    private val _session = MutableStateFlow<UserModel?>(null)
    init {
        viewModelScope.launch {
            repository.getSession().collect { userModel ->
                if (_session.value != userModel) {
                    _session.value = userModel
                    getDataBengkel()
                }
            }

        }
    }
    private suspend fun getDataBengkel(){
        _loading.value = true
        try {
            val response = ApiConfig.getApiService(_session.value!!.token).getLocBengkel(0.0,0.0)
            _itemsStateFlow.value = response.data!!
            _itemsStateFlow.value.sortedBy { it.rating }
        } catch (e: HttpException) {
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {
            _loading.value = false
        }
    }
}