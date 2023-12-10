package com.hiservice.mobile.screen.authentication.login

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.model.AlertData
import com.hiservice.mobile.data.model.UserModel
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : ViewModel() {
    val auth = Firebase.auth
    private val _email = mutableStateOf("")
    val email: State<String> get() = _email
    private val _alert = mutableStateOf(false)
    val alert: State<Boolean> get() = _alert

    private val _alertData = mutableStateOf(AlertData("Default","Default", Icons.Outlined.Add))
    val alertData: State<AlertData> get() = _alertData
    fun alertStatus(newStatus: Boolean) {
        _alert.value = newStatus
    }
    fun emailText(newText: String) {
        _email.value = newText
    }
    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading
    private val _password = mutableStateOf("")
    val password: State<String> get() = _password
    fun passwordText(newText: String) {
        _password.value = newText
    }


    fun loginFunction() {
        _loading.value = true
        try{
            auth.signInWithEmailAndPassword(_email.value, _password.value)
                .addOnCompleteListener { task ->
                    _loading.value = false
                    var message = ""
                    if (task.isSuccessful) {
                    } else {
                        if(task.exception.toString().contains("The email address is badly formatted.")){
                            message = "Format email tidak sesuai"
                        }else if(task.exception.toString().contains("There is no user record corresponding to this identifier.")){
                            message = "Email dan Password Salah"
                        }else if(task.exception.toString().contains("The supplied auth credential is incorrect, malformed or has expired.")){
                            message = "Email dan Password Salah"
                        }else if(task.exception.toString().contains("We have blocked all requests from this device due to unusual activity. Try again later.")){
                            message = "Akses anda di block sementara, coba beberapa saat lagi"
                        }else{
                            message = "Error tidak ditemukan dengan kode " + task.exception.toString()
                        }
                        _alertData.value = AlertData("Failed!",message,Icons.Filled.Warning)
                        _alert.value = true
                    }
                }
        }catch (e : Exception){
            _alertData.value = AlertData("Failed!","Username / Password tidak boleh kosong",Icons.Filled.Warning)
            _alert.value = true
            _loading.value = false
        }

    }



}