package com.hiservice.mobile.screen.afterlogin.profil

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.hiservice.mobile.data.Repository

class ProfilViewModel(private val repository: Repository) : ViewModel(){
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _email = mutableStateOf("example@gmail.com")
    val email: State<String> get() = _email

    init {
        _email.value = Firebase.auth.currentUser?.email.toString()
    }
}