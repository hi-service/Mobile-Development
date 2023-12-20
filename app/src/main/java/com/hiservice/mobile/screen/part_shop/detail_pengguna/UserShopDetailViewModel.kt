package com.hiservice.mobile.screen.part_shop.detail_pengguna

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.model.UserModel
import com.hiservice.mobile.data.retrofit.ApiConfig
import com.hiservice.mobile.data.retrofit.gson.DataBengkelShopItem
import com.hiservice.mobile.data.retrofit.gson.DataListBengkel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserShopDetailViewModel(val repository: Repository) : ViewModel() {
    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading

    private val _itemsStateFlow = MutableStateFlow<List<DataBengkelShopItem>>(mutableListOf())
    val itemsStateFlow: StateFlow<List<DataBengkelShopItem>> get() = _itemsStateFlow

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
            val response = ApiConfig.getApiService(_session.value!!.token).getBengkelShop(0.0,0.0)
            _itemsStateFlow.value = response.dataBengkelShop!!

        } catch (e: HttpException) {
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {
            _loading.value = false
        }
    }
}