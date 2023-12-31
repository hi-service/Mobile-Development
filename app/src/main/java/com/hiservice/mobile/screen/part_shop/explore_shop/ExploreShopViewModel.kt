package com.hiservice.mobile.screen.part_shop.explore_shop

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.model.UserModel
import com.hiservice.mobile.data.retrofit.ApiConfig
import com.hiservice.mobile.data.retrofit.gson.DataBengkelShopItem
import com.hiservice.mobile.data.retrofit.gson.DataItemsInventory
import com.hiservice.mobile.data.retrofit.gson.DeskripsiBengkelItem
import com.hiservice.mobile.data.retrofit.gson.DetailBengkel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ExploreShopViewModel(val repository: Repository) : ViewModel() {
    private val _dataBengkelItem = MutableStateFlow<List<DataItemsInventory>>(mutableListOf())
    val dataBengkelItem: StateFlow<List<DataItemsInventory>> get() = _dataBengkelItem

    private val _isSuccess = mutableStateOf(false)
    val isSuccess: State<Boolean> get() = _isSuccess
    private val _message = mutableStateOf("")
    val message: State<String> get() = _message
    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading

    private val _session = MutableStateFlow<UserModel?>(null)
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
            val response = ApiConfig.getApiService(_session.value!!.token).getItemsData(id)
            _dataBengkelItem.value = response.data!!
        } catch (e: HttpException) {
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {
            _loading.value = false
        }
    }
}