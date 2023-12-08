package com.hiservice.mobile.data

import androidx.lifecycle.LiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.hiservice.mobile.data.localstorage.UserPref
import com.hiservice.mobile.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository private constructor(
    private val preference: UserPref,
) {

    suspend fun saveSession(user: UserModel) {
        preference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return preference.getSession()
    }

    suspend fun logout() {
        preference.logout()
    }
    val auth = Firebase.auth

    val email = "user@example.com"
    val password = "password"



    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            preference: UserPref,
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(preference)
            }
    }
}