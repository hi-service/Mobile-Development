package com.hiservice.mobile.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.hiservice.HiServiceApps
import com.hiservice.mobile.MainActivity
import com.hiservice.mobile.R

class FirebaseNotification : FirebaseMessagingService() {

    private val tAG = "tagged"
    val myMessage = "myMessage"
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.notification != null) {
            val title = message.notification!!.title
            val body = message.notification!!.body
            val intentIs = Intent(this, MainActivity::class.java)
            val pi: PendingIntent = PendingIntent.getActivity(
                this, 0, intentIs, PendingIntent.FLAG_MUTABLE)
            val notificationBuilder = NotificationCompat.Builder(this, HiServiceApps().channelId)
                .setSmallIcon(R.drawable.logo_apps)
                .setContentTitle(title)
                .setColor(1)
                .setContentText(body)
                .setContentIntent(pi)
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText("Keterangan pesanan :"))
                .setAutoCancel(true)
                .build()

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(1002, notificationBuilder)
        }
    }

    //
    override fun onDeletedMessages() {
        super.onDeletedMessages()
        Log.d(tAG, "onDeletedMessages Called")
    }

    // By any cause if a new Token is generated this function will be call
    // to update token on our server (Monitoring)
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(tAG, "onNewToken Called")
    }
}