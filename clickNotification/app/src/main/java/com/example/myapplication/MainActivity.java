package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btndendNotification = findViewById(R.id.btn_send_lisst);
        Button btngotoList = findViewById(R.id.btn_goto_lisst);
        btndendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPushNotification();
            }
        });
        btngotoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListProductActivity.class);
                startActivity(intent);
            }
        });
    }

    private void sendPushNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        // cài đặt nhạc chuông cần có file URi
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
// xuwr lys cghuyen

        Intent result = new Intent(this,detailActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(result);
        PendingIntent resultPending =
                stackBuilder.getPendingIntent(getNotificationId(),PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, Application.CHANNEL_ID)
                .setContentTitle("Title Push Notification")
                .setContentText("Message push Notification")
                //.setSmallIcon(R.) ảnh hiện thi khi thông báo
                // có thể add ảnh
                .setSound(uri)// set nhạc
                .setAutoCancel(true)// khi click vaof chuyen trang vaf xoa thong bao di
                .setContentIntent(resultPending)//nhows addd vaof ddeer chuyen
                .setLargeIcon(bitmap)
//                .setColor()
                .build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification);

    }
    private int getNotificationId() {
        return (int) new Date().getTime();
    }
}