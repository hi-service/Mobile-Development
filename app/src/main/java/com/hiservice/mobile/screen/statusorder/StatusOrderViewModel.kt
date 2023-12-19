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
import com.hiservice.mobile.data.retrofit.gson.ChatItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _idBengkel = mutableStateOf("")
    private val _orderId = mutableStateOf(0)
    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading

    private val _textSend = mutableStateOf("")
    val textSend: State<String> get() = _textSend

    fun textSendChange(newText: String){
        _textSend.value = newText
    }

    private val _isFinished = mutableStateOf(false)
    val isFinished: State<Boolean> get() = _isFinished


    private val _isCancel = mutableStateOf(false)
    val isCancel: State<Boolean> get() = _isCancel

    fun setCancel(){
        _isCancel.value = false
    }
    private val _countRating = mutableStateOf(0)
    val countRating: State<Int> get() = _countRating
    fun setCountRating(newCount : Int){
        _countRating.value = newCount +1
    }

    fun setFinish(){
        _isFinished.value = false
    }
    private val _textPenilaian = mutableStateOf("")
    val textPenilaian: State<String> get() = _textPenilaian

    fun setTextPenilaian(newText : String){
        _textPenilaian.value = newText
    }

    val orderStatus: Flow<StatusOrderModel> get() = _orderStatus
    private val _session = MutableStateFlow<UserModel?>(null)

    private val _chatModel = MutableStateFlow<List<ChatItem>>(mutableListOf())
    val chatModel: StateFlow<List<ChatItem>> get() = _chatModel
    init {
        viewModelScope.launch {
            repository.getSession().collect { userModel ->
                if (_session.value != userModel) {
                    _session.value = userModel
                    getDataOrder()
                    startFetchingData()
                }
            }
        }
    }
    fun startFetchingData() {
        uiScope.launch {
            while (true) {
                getDataChat()
                delay(1000)
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
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
    fun setOrderRating(){
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = ApiConfig.getApiService(_session.value!!.token).setRating(_countRating.value,_idBengkel.value, statement = _textPenilaian.value)
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
            _orderId.value = response.orderData!!.orderId!!.toInt()
            if(response.orderData.status == "waiting"){
                _orderStatus.value = StatusOrderModel(
                    statusOrder = response.orderData.status!!, idOrder = response.orderData.orderId!!, time = response.orderData.waktu!!,
                    image = R.drawable.statusorder_waiting, text = "Dalam proses...", subText = "Harap tunggu, pesanan anda sedang diproses", isButton = true, buttonColor = Color.Red, buttonText = "Batalkan", buttonClick = {
                        _isCancel.value = true
                    })
            }else if(response.orderData.status == "ongoing"){
                _orderStatus.value = StatusOrderModel(
                    statusOrder = response.orderData.status!!, idOrder = response.orderData.orderId!!, time = response.orderData.waktu!!,
                    image = R.drawable.statusorder_progress, text = "Pesanan Diterima", subText = "Orang dari bengkel akan menuju ke lokasi anda", isButton = false ,buttonColor = Color.Red, buttonText = "Hubungi Bengkel", buttonClick = {
                        setOrderStatusStatus("")
                    })
            }else if(response.orderData.status == "rejected"){
                _orderStatus.value = StatusOrderModel(
                    statusOrder = response.orderData.status!!, idOrder = response.orderData.orderId!!, time = response.orderData.waktu!!,
                    image = R.drawable.statusorder_canceled, text = "Pesanan Ditolak", subText = "Maaf pesanan anda ditolak. Silahkan pilih bengkel lain", isButton = true, buttonColor = Color.DarkGray, buttonText = "Selesaikan Pesanan", buttonClick = {
                        setOrderStatusStatus("rejected")
                    })
            }else if(response.orderData.status == "finished"){
                _orderStatus.value = StatusOrderModel(
                    statusOrder = response.orderData.status!!, idOrder = response.orderData.orderId!!, time = response.orderData.waktu!!,
                    image = R.drawable.statusorder_success, text = "Pesanan Selesai", subText = "Order diselesaikan, silahkan konfirmasi order dibawah untuk melakukan order lain", isButton = true, buttonColor = Color(0xFF34B580), buttonText = "Selesaikan", buttonClick = {
                        setOrderStatusStatus("finished")
                        _isFinished.value = true
                    })
            }
            _idBengkel.value = response.orderData.idLocation!!

        } catch (e: HttpException) {
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {

        }
    }
    /* Order Chat Status */
    private suspend fun getDataChat(){
        try {
            val response = ApiConfig.getApiService(_session.value!!.token).getChat(_orderId.value)
            _chatModel.value = response.chat!!
        } catch (e: HttpException) {
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {

        }
    }
    suspend fun sendDataChat(){
        try {
            val response = ApiConfig.getApiService(_session.value!!.token).sendMessage(id_order = _orderId.value, message = _textSend.value)
            _textSend.value = ""
            getDataChat()
        } catch (e: HttpException) {
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {

        }
    }
}