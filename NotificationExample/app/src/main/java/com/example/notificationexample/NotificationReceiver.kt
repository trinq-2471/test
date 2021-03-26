package com.example.notificationexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"Click button on Notification",Toast.LENGTH_LONG)

        var notificationManager = NotificationManagerCompat.from(context!!)
        notificationManager.cancel(1)
    }

}