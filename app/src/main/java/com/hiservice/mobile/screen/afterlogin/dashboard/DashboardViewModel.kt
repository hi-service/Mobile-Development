package com.hiservice.mobile.screen.afterlogin.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.hiservice.mobile.data.Repository
import kotlinx.coroutines.launch

class DashboardViewModel(val repository: Repository) : ViewModel() {
     fun logout(){
        viewModelScope.launch {
            repository.logout()
            Firebase.auth.signOut()
        }

    }
}