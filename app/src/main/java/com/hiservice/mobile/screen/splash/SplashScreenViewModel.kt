package com.hiservice.mobile.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel(private val repository: Repository) : ViewModel() {
    private val _session = MutableStateFlow<UserModel?>(null)
    val session: Flow<UserModel?> get() = _session
    init {
        viewModelScope.launch {
            repository.getSession().collect { userModel ->
                if (_session.value != userModel) {
                    _session.value = userModel
                }
            }
        }
    }



}