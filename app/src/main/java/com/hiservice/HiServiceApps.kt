package com.hiservice

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.hiservice.mobile.MainActivity
import kotlin.system.exitProcess

class HiServiceApps : Application() {

    override fun onCreate() {
        super.onCreate()
    }


    override fun onLowMemory() {
        super.onLowMemory()
    }
}