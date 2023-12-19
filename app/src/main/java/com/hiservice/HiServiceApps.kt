package com.hiservice

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class HiServiceApps : Application() {
    val channelId = "NotificationChannel"
    private val channelName = "NotificationChannel"
    private val channelDesc = "Creating Notification Channel for versions above Oreo"
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(channelId,channelName, NotificationManager.IMPORTANCE_HIGH)
        channel.description = channelDesc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}