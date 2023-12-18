package com.hiservice.mobile.screen.statusorder

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiservice.mobile.R
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.model.StatusOrderModel
import com.hiservice.mobile.data.model.UserModel
import com.hiservice.mobile.data.retrofit.ApiConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class StatusOrderViewModel(val repository: Repository) : ViewModel() {
    private val _orderStatus = MutableStateFlow(StatusOrderModel(
        statusOrder = "Unknown",
        idOrder = "Unknown",
        time = "",
        image = R.drawable.statusorder_waiting,
        text = "",
        subText = "",
        isButton = false
    ))
    val orderStatus: Flow<StatusOrderModel> get() = _orderStatus
    private val _session = MutableStateFlow<UserModel?>(null)
    val session: Flow<UserModel?> get() = _session
    private val _userName = mutableStateOf("XXXXXXXXX")

    init {
        viewModelScope.launch {
            repository.getSession().collect { userModel ->
                if (_session.value != userModel) {
                    _session.value = userModel
                    getDataOrder()
                }
            }
        }
    }
    fun setOrderStatusStatus(){
        try {


        } catch (e: HttpException) {
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {

        }
    }
    private suspend fun getDataOrder(){
        try {
            val response = ApiConfig.getApiService(_session.value!!.token).getOrderStatus()
            if(response.orderData!!.status == "waiting"){
                _orderStatus.value = StatusOrderModel(
                    statusOrder = response.orderData?.status!!, idOrder = response.orderData.orderId!!, time = response.orderData.waktu!!,
                    image = R.drawable.statusorder_waiting, text = "Dalam proses...", subText = "Harap tunggu, pesanan anda sedang diproses", isButton = true)
            }else if(response.orderData!!.status == "ongoing"){
                _orderStatus.value = StatusOrderModel(
                    statusOrder = response.orderData?.status!!, idOrder = response.orderData.orderId!!, time = response.orderData.waktu!!,
                    image = R.drawable.statusorder_progress, text = "Pesanan Diterima", subText = "Orang dari bengkel akan menuju ke lokasi anda", isButton = false)
            }else if(response.orderData!!.status == "rejected"){
                _orderStatus.value = StatusOrderModel(
                    statusOrder = response.orderData?.status!!, idOrder = response.orderData.orderId!!, time = response.orderData.waktu!!,
                    image = R.drawable.statusorder_canceled, text = "Pesanan Ditolak", subText = "Maaf pesanan anda ditolak. Silahkan pilih bengkel lain", isButton = true)
            }else if(response.orderData!!.status == "finished"){
                _orderStatus.value = StatusOrderModel(
                    statusOrder = response.orderData?.status!!, idOrder = response.orderData.orderId!!, time = response.orderData.waktu!!,
                    image = R.drawable.statusorder_success, text = "Pesanan Selesai", subText = "Order diselesaikan, silahkan konfirmasi order dibawah untuk melakukan order lain", isButton = true)
            }

        } catch (e: HttpException) {
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {

        }
    }
}