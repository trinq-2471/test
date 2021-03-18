package com.example.notificationexample

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class MyApplication : Application() {

    companion object{
        fun CHANNEL_ID_GAME() : String = "Channel Game"
        fun CHANNEL_ID_JOB() : String = "Channel Job"
    }

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(MyApplication.CHANNEL_ID_GAME(), name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

}