package com.hiservice.mobile.screen.authentication.register

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.hiservice.mobile.data.Repository

class RegisterViewModel(private val repository: Repository) : ViewModel() {
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


    fun registerFunction() {
        _loading.value = true
        auth.createUserWithEmailAndPassword(_email.value, _password.value)
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