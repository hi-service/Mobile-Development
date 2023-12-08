package com.hiservice.mobile.data.injection

import android.content.Context
import com.hiservice.mobile.data.Repository
import com.hiservice.mobile.data.localstorage.UserPref
import com.hiservice.mobile.data.localstorage.dataStore


object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPref.getInstance(context.dataStore)
        return Repository.getInstance(pref)
    }
}