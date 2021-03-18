package com.example.notificationexample

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNotifi.setOnClickListener(View.OnClickListener {
            sendNotification()
        })
    }

    private fun sendNotification() {
        val notification = NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_GAME())
                .setContentTitle("This is title notification")
                .setContentText("This is content notification")
                .setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setColor(resources.getColor(R.color.red))
                .build()

        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(getNotificationId(), notification)

//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.notify(getNotificationId(), notification)

    }

    private fun getNotificationId(): Int {
        return Date().time.toInt()
    }

}