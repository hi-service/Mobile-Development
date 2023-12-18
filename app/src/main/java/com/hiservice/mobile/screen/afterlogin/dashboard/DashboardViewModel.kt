package com.hiservice.mobile.screen.afterlogin.dashboard

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.model.AlertData
import com.hiservice.mobile.data.model.UserModel
import com.hiservice.mobile.data.retrofit.ApiConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DashboardViewModel(val repository: Repository) : ViewModel() {

    private val _session = MutableStateFlow<UserModel?>(null)
    private val _userName = mutableStateOf("XXXXXXXXX")
    val userName: State<String> get() = _userName

    private val _orderStatus = mutableStateOf("XXXXXXXXX")
    val orderStatus: State<String> get() = _orderStatus
    private val _buyStatus = mutableStateOf("XXXXXXXXX")
    val buyStatus: State<String> get() = _buyStatus
    init {
        viewModelScope.launch {
            repository.getSession().collect { userModel ->
                if (_session.value != userModel) {
                    _session.value = userModel
                    getUserData()
                }
            }

        }
    }
    private suspend fun getUserData(){
            try {
                val response = ApiConfig.getApiService(_session.value!!.token).getUserData()
                _userName.value = response.data!!.nama!!
                _buyStatus.value = response.data.statusBuyOrder!!
                _orderStatus.value = response.data.statusOrder!!
            } catch (e: HttpException) {
                viewModelScope.launch {
                    repository.logout()
                    Firebase.auth.signOut()
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("Exception", it) }
            }finally {
            }
    }
     fun logout(){
        viewModelScope.launch {
            repository.logout()
            Firebase.auth.signOut()
        }

    }
}