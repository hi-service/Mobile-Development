package com.hiservice.mobile.screen.authentication.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.model.UserModel
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : ViewModel() {
    private val _email = mutableStateOf("")
    val email: State<String> get() = _email
    fun emailText(newText: String) {
        _email.value = newText
    }
    fun settingses(){

    }


    suspend fun getSession() {
        repository.getSession().collect { games ->

        }
    }



}