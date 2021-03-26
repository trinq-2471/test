package com.example.notificationexample

import android.app.Notification.VISIBILITY_PUBLIC
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.RemoteViews
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.security.PublicKey
import java.util.*


class MainActivity : AppCompatActivity() {

    val CONTEND_TITLE_CHANEL_GAME = "Thông báo thông tin trò chơi"
    val CONTENT_NOTIFICATION_CHANEL_GAME = "Chúng tôi tin rằng những nhà phát triển trò chơi xứng đáng có thêm một lựa chọn nữa. Đó là lý do vì sao chúng tôi " +
            "xây dựng Lumberyard: một nền tảng trò chơi không có phí chuyển nhượng và phí đặt chỗ, tích hợp trơn tru với Twitch và AWS, cùng rất nhiều tính năng khác nữa."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNotifiChannelGame.setOnClickListener(View.OnClickListener {
            sendNotificationChanelGame()
        })

        btnNotifiChannelJob.setOnClickListener(View.OnClickListener {
            sendNotificationChannelJob()
        })

        btnNotifiChannelMusic.setOnClickListener(View.OnClickListener {
            sendNotificationChannelMusic()
        })
    }

    private fun sendNotificationChanelGame() {

        val bitmap : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.vu)
        val uri : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val intentJob = Intent(this,MainActivity::class.java)
        val  contendIntent = PendingIntent.getActivity(this,0, intentJob,0)

        val notification = NotificationCompat.Builder(this)
                .setContentTitle(CONTEND_TITLE_CHANEL_GAME)
                .setContentText(CONTENT_NOTIFICATION_CHANEL_GAME)
                .setSmallIcon(R.drawable.ic_baseline_videogame_asset_24)
                .setLargeIcon(bitmap)
//                .setStyle(NotificationCompat.BigTextStyle().bigText(CONTENT_NOTIFICATION_CHANEL_GAME))
                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null))
                .setColor(resources.getColor(R.color.red))
                .setAutoCancel(true)
                .setContentIntent(contendIntent)
//                .setSound(uri)
                .build()

        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(getNotificationId(), notification)
    }

    private fun sendNotificationChannelJob() {
        var bitmap : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.job)
        val uri : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val intentJob = Intent(this,MainActivity::class.java)
        val  contendIntent = PendingIntent.getActivity(this,0, intentJob,0)

        val notification = NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_JOB())
                .setContentTitle("Thông báo thông tin công việc")
                .setContentText("Nhớ báo cáo kế hoạch hằng ngày")
                .setSmallIcon(R.drawable.ic_baseline_work_24)
                .setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.imgforest))
                .setContentIntent(contendIntent)
                .setColor(resources.getColor(R.color.blue))
                .addAction(R.drawable.ic_baseline_work_24,"Job",contendIntent)
                .addAction(R.drawable.ic_baseline_access_alarm_24,"Alarm",contendIntent)
                .addAction(R.drawable.ic_baseline_music_note_24,"Music",contendIntent)
                .setSound(uri)
                .build()

        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(getNotificationId(), notification)
    }

    private fun sendNotificationChannelMusic(){

        val intentMusic = Intent(this,MainActivity::class.java)
        val contendIntent = PendingIntent.getActivity(this,0, intentMusic,0)

        val viewCustom = RemoteViews(packageName,R.layout.custom_layout)
        viewCustom.setOnClickPendingIntent( R.id.songImgCustom, contendIntent)

        val viewExpand = RemoteViews(packageName,R.layout.expand_layout)
        viewExpand.setOnClickPendingIntent( R.id.songImgExpand, contendIntent)

        val notification = NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_MUSIC())
                .setSmallIcon(R.drawable.ic_baseline_music_note_24)
                .setColor(resources.getColor(R.color.red))
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(viewCustom)
                .setCustomBigContentView(viewExpand)
                .setAutoCancel(true)
                .build()

        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(getNotificationId(), notification)
    }

    private fun getNotificationId(): Int {
        return Date().time.toInt()
    }
}