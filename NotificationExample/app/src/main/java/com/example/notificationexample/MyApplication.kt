package com.example.notificationexample

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi

class MyApplication : Application() {

    companion object{
        fun CHANNEL_ID_GAME() = "Channel Game"
        fun CHANNEL_ID_JOB() = "Channel Job"
        fun CHANNEL_ID_MUSIC() = "Channel Music"
    }

    override fun onCreate() {
        super.onCreate()

        // Create the NotificationChannel
        createNotificationChannel()
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Create the NotificationChannelGame
            val uri : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val nameChanelGame = getString(R.string.channel_game_name)
            val descriptionTextChanelGame = getString(R.string.channel_game_description)
            val importanceChanelGame = NotificationManager.IMPORTANCE_DEFAULT
            val mChannelGame = NotificationChannel(MyApplication.CHANNEL_ID_GAME(), nameChanelGame, importanceChanelGame)
            mChannelGame.description = descriptionTextChanelGame


            // Create the NotificationChannelJob
            val soundIphoneUri : Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.iphonesound)
            val attributes : AudioAttributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build()

            val nameChanelJob = getString(R.string.channel_job_name)
            val descriptionTextChanelJob = getString(R.string.channel_job_description)
            val importanceChanelJob = NotificationManager.IMPORTANCE_DEFAULT
            val mChannelJob = NotificationChannel(MyApplication.CHANNEL_ID_JOB(), nameChanelJob, importanceChanelJob)
            mChannelJob.description = descriptionTextChanelJob
            mChannelJob.setSound(soundIphoneUri, attributes)

            // Create the NotificationChannelJob
            val nameChanelMusic = "Channel Music"
            val descriptionTextChanelMusic = "Channel để nghe nhạc"
            val importanceChanelMusic = NotificationManager.IMPORTANCE_HIGH
            val mChannelMusic = NotificationChannel(MyApplication.CHANNEL_ID_MUSIC(), nameChanelMusic, importanceChanelMusic)
            mChannelMusic.description = descriptionTextChanelMusic

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannelGame)
            notificationManager.createNotificationChannel(mChannelJob)
            notificationManager.createNotificationChannel(mChannelMusic)
        }
    }
}