package com.hiservice.mobile.screen.authentication.register

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.model.AlertData
import com.hiservice.mobile.data.retrofit.ApiConfig
import com.hiservice.mobile.util.AlertMessages.Companion.RegisterAlertMessages
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(private val repository: Repository) : ViewModel() {
    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading
    val auth = Firebase.auth


    private val _email = mutableStateOf("")
    val email: State<String> get() = _email
    fun emailText(newText: String) {
        _email.value = newText
    }

    private val _name = mutableStateOf("")
    val name: State<String> get() = _name
    fun nameText(newText: String) {
        _name.value = newText
    }
    private val _alert = mutableStateOf(false)
    val alert: State<Boolean> get() = _alert

    private val _alertData = mutableStateOf(AlertData("Default","Default", Icons.Outlined.Add))
    val alertData: State<AlertData> get() = _alertData
    fun alertStatus(newStatus: Boolean) {
        _alert.value = newStatus
    }

    private val _password = mutableStateOf("")
    val password: State<String> get() = _password
    fun passwordText(newText: String) {
        _password.value = newText
    }
    private suspend fun registerToApi(user_id:String){
        _loading.value = true
        try {
            val response = ApiConfig.getApiService().registerRequest(user_id,_name.value)
            _alertData.value = AlertData("Sukses !", response.message.toString(),Icons.Filled.CheckCircle)
            _alert.value = true
        } catch (e: HttpException) {
            _alertData.value = AlertData("Failed!",e.message(),Icons.Filled.Warning)
            _alert.value = true
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {
            _loading.value = false
        }
        }
    fun registerFunction() {
        _loading.value = true
        auth.createUserWithEmailAndPassword(_email.value, _password.value)
            .addOnCompleteListener { task ->
                _loading.value = false
                var message = ""
                if (task.isSuccessful) {
                    viewModelScope.launch {
                        registerToApi(task.result.user!!.uid)
                    }
                } else {
                    RegisterAlertMessages(task.exception.toString())
                    _alertData.value = AlertData("Failed!",message,Icons.Filled.Warning)
                    _alert.value = true
                }
            }
    }



}