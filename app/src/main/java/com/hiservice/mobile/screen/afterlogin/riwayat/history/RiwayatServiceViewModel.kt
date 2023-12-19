package com.hiservice.mobile.screen.afterlogin.riwayat.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.model.Keluhan
import com.hiservice.mobile.data.model.UserModel
import com.hiservice.mobile.data.retrofit.ApiConfig
import com.hiservice.mobile.data.retrofit.gson.ChatItem
import com.hiservice.mobile.data.retrofit.gson.DataHistory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RiwayatServiceViewModel(private val repository: Repository) : ViewModel() {


    private val _session = MutableStateFlow<UserModel?>(null)
    private val _historyData = MutableStateFlow<List<DataHistory>>(mutableListOf())
    val historyData: StateFlow<List<DataHistory>> get() = _historyData
    init {
        viewModelScope.launch {
            repository.getSession().collect { userModel ->
                if (_session.value != userModel) {
                    _session.value = userModel
                    getHistoryData()
                }
            }

        }
    }
    private suspend fun getHistoryData(){
        try {
            val response = ApiConfig.getApiService(_session.value!!.token).getOrderHistory()
            _historyData.value = response.data!!
        } catch (e: HttpException) {
            e.message?.let { Log.e("Exception", it) }
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {

        }
    }
}