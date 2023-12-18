package com.hiservice.mobile.screen.afterlogin.services.detail_bengkel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.model.SharedData
import com.hiservice.mobile.data.model.UserModel
import com.hiservice.mobile.data.retrofit.ApiConfig
import com.hiservice.mobile.data.retrofit.gson.DataListBengkel
import com.hiservice.mobile.data.retrofit.gson.DeskripsiBengkelItem
import com.hiservice.mobile.data.retrofit.gson.DetailBengkel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailBengkelViewModel(private val repository: Repository) : ViewModel() {
    private val _dataBengkel = mutableStateOf<DetailBengkel?>(null)
    val dataBengkel: State<DetailBengkel?> get() = _dataBengkel
    private val _dataBengkelItem = MutableStateFlow<List<DeskripsiBengkelItem>>(mutableListOf())
    val dataBengkelItem: StateFlow<List<DeskripsiBengkelItem>> get() = _dataBengkelItem

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading

    private val _session = MutableStateFlow<UserModel?>(null)
    val session: Flow<UserModel?> get() = _session
    private val _userName = mutableStateOf("XXXXXXXXX")

    init {
        viewModelScope.launch {
            repository.getSession().collect { userModel ->
                if (_session.value != userModel) {
                    _session.value = userModel
                }
            }

        }
    }

    fun setDataBengkel(id: Int){
        viewModelScope.launch {
            getDataBengkel(id)
        }
    }
    private suspend fun getDataBengkel(id: Int){
        _loading.value = true
        try {
            val response = ApiConfig.getApiService(_session.value!!.token).getDesc(id)
            _dataBengkel.value = response!!
            _dataBengkelItem.value = response.data!!
        } catch (e: HttpException) {
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {
            _loading.value = false
        }
    }

     fun setOrder(id: Int ,dataOrder : SharedData){
        _loading.value = true
        viewModelScope.launch {
        try {
            val response = ApiConfig.getApiService(_session.value!!.token).setOrder(id,dataOrder.deskripsiMasalah,dataOrder.noHp,dataOrder.lat,dataOrder.lng,dataOrder.alamat)

        } catch (e: HttpException) {
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {
            _loading.value = false
        }
    }
    }
}

