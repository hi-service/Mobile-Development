package com.hiservice.mobile.screen.statusorder

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
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
        isButton = false,
        buttonText = "",
        buttonColor = Color.Red,
        buttonClick = {}
    ))

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading

    private val _isFinished = mutableStateOf(false)
    val isFinished: State<Boolean> get() = _isFinished

    private val _countRating = mutableStateOf(0)
    val countRating: State<Int> get() = _countRating
    fun setCountRating(newCount : Int){
        _countRating.value = newCount +1
    }

    fun inTerface(){
        _isFinished.value = false
    }
    private val _textPenilaian = mutableStateOf("")
    val textPenilaian: State<String> get() = _textPenilaian

    fun setTextPenilaian(newText : String){
        _textPenilaian.value = newText
    }

    val orderStatus: Flow<StatusOrderModel> get() = _orderStatus
    private val _session = MutableStateFlow<UserModel?>(null)

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
     fun setOrderStatusStatus(status : String){
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = ApiConfig.getApiService(_session.value!!.token).setOrderStatusDone(status)
            } catch (e: HttpException) {
            } catch (e: Exception) {
                e.message?.let { Log.e("Exception", it) }
            }finally {
                _loading.value = false
            }
        }

    }
    private suspend fun getDataOrder(){
        try {
            val response = ApiConfig.getApiService(_session.value!!.token).getOrderStatus()
            if(response.orderData!!.status == "waiting"){
                _orderStatus.value = StatusOrderModel(
                    statusOrder = response.orderData?.status!!, idOrder = response.orderData.orderId!!, time = response.orderData.waktu!!,
                    image = R.drawable.statusorder_waiting, text = "Dalam proses...", subText = "Harap tunggu, pesanan anda sedang diproses", isButton = true, buttonColor = Color.Red, buttonText = "Batalkan", buttonClick = {
                        setOrderStatusStatus("canceled")
                    })
            }else if(response.orderData!!.status == "ongoing"){
                _orderStatus.value = StatusOrderModel(
                    statusOrder = response.orderData?.status!!, idOrder = response.orderData.orderId!!, time = response.orderData.waktu!!,
                    image = R.drawable.statusorder_progress, text = "Pesanan Diterima", subText = "Orang dari bengkel akan menuju ke lokasi anda", isButton = false ,buttonColor = Color.Red, buttonText = "Hubungi Bengkel", buttonClick = {
                        setOrderStatusStatus("")
                    })
            }else if(response.orderData!!.status == "rejected"){
                _orderStatus.value = StatusOrderModel(
                    statusOrder = response.orderData?.status!!, idOrder = response.orderData.orderId!!, time = response.orderData.waktu!!,
                    image = R.drawable.statusorder_canceled, text = "Pesanan Ditolak", subText = "Maaf pesanan anda ditolak. Silahkan pilih bengkel lain", isButton = true, buttonColor = Color.DarkGray, buttonText = "Selesaikan Pesanan", buttonClick = {
                        setOrderStatusStatus("rejected")
                    })
            }else if(response.orderData!!.status == "finished"){
                _orderStatus.value = StatusOrderModel(
                    statusOrder = response.orderData?.status!!, idOrder = response.orderData.orderId!!, time = response.orderData.waktu!!,
                    image = R.drawable.statusorder_success, text = "Pesanan Selesai", subText = "Order diselesaikan, silahkan konfirmasi order dibawah untuk melakukan order lain", isButton = true, buttonColor = Color(0xFF34B580), buttonText = "Selesaikan", buttonClick = {
                        _isFinished.value = true
                    })
            }

        } catch (e: HttpException) {
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {

        }
    }
}