package com.hiservice.mobile.screen.authentication.login

import android.util.Log
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
import com.hiservice.mobile.data.model.UserModel
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : ViewModel() {
    val auth = Firebase.auth
    private val _email = mutableStateOf("")
    val email: State<String> get() = _email
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
        auth.signInWithEmailAndPassword(_email.value, _password.value)
            .addOnCompleteListener { task ->
                _loading.value = false
                if (task.isSuccessful) {
                    Log.d("We","Benar")
                } else {
                    Log.d("We","Salah")
                }
            }
    }



}