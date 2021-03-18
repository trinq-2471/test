package com.example.notificationjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNotifi = findViewById(R.id.btnNotifi);

        btnNotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
    }

    private void sendNotification(){

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Notification notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
                .setContentTitle("This is title notification")
                .setContentText("This is content notification")
                .setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setColor(getResources().getColor(R.color.red))
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(),notification);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(getNotificationId(), notification);
    }

    private int getNotificationId(){
        return (int) new Date().getTime();
    }

}