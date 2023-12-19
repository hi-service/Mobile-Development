package com.hiservice.mobile.screen.afterlogin.profil

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.model.UserModel
import com.hiservice.mobile.data.retrofit.ApiConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException


class ProfilViewModel(private val repository: Repository) : ViewModel(){

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading
    private val _email = mutableStateOf("example@gmail.com")
    val email: State<String> get() = _email
    private val _session = MutableStateFlow<UserModel?>(null)
    private val _nama = mutableStateOf("")

    val nama: State<String> get() = _nama
    private val _noHp = mutableStateOf("")

    val noHp: State<String> get() = _noHp
    init {
        viewModelScope.launch {
            repository.getSession().collect { userModel ->
                if (_session.value != userModel) {
                    _session.value = userModel
                    getUserData()
                    val firebaseAuth = FirebaseAuth.getInstance()
                    val currentUser = firebaseAuth.currentUser
                    if (currentUser != null) {
                        _email.value = currentUser?.email!!
                        // Use the email as needed
                    }


                }
            }

        }
    }
    suspend fun getUserData(){
        try {
            _loading.value = true
            val response = ApiConfig.getApiService(_session.value!!.token).getUserData()
            _nama.value = response.data?.nama!!
            _noHp.value = response.data.nohp!!
        } catch (e: HttpException) {
            viewModelScope.launch {
                repository.logout()
                Firebase.auth.signOut()
            }
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }finally {
            _loading.value = false
        }
    }

}