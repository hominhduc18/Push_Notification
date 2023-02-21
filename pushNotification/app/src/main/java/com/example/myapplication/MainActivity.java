package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final Object NOTIFICATION_ID = 1;

    private  static final String TITLE_PUSH_NOFICATION =" aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    private  static final String TITLE_PUSH_Content =" bbbbbbbbbbbbbbbbbbbaa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button btnsendnotification =  findViewById(R.id.btn_send_notification);
        Button btnsend_2 = findViewById(R.id.btn_send_notification_2);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnsend_3 = findViewById(R.id.btn_send_notification_3);
        btnsendnotification .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNofication();
            }
        });
        btnsend_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNofication2();
            }
        });
        btnsend_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNofication3();
            }
        });
    }
// đúng với bản cũ 8.0 trở xuống
//    private void sendNofication() {
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        Notification notification = new Notification.Builder(this)
//                .setContentTitle("Title Push Notification")
//                .setContentText("Message push Notification")
//                //.setSmallIcon(R.) ảnh hiện thi khi thông báo
//                // có thể add ảnh
//                .setLargeIcon(bitmap)
////                .setColor()
//                .build();
//
//         NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//         if(notification !=null) {
//             notification.notify(NOTIFICATION_ID, notification);
//         }
//    }

    // đôi vs bán 8 trở lên

    private void sendNofication() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        // cài đặt nhạc chuông cần có file URi
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification notification = new NotificationCompat.Builder(this, Application.CHANNEL_ID)
                .setContentTitle("Title Push Notification")
                .setContentText("Message push Notification")
                //.setSmallIcon(R.) ảnh hiện thi khi thông báo
                // có thể add ảnh
                .setSound(uri)// set nhạc
                .setLargeIcon(bitmap)
//                .setColor()
                .build();
        NotificationManagerCompat  notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification);
// cách 2 ko cần check null
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if(notificationManager !=null) {
//            notificationManager.notify(getNotificationId(), notification);
//        }
    }

    private void sendNofication2() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        // file nhạc tự add
        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tiengdong_com);
        Notification notification = new NotificationCompat.Builder(this, Application.CHANNEL_ID_2)
                .setContentTitle("Title Push Notification_2")
                .setContentText("Message push Notification_2")
                //.setSmallIcon(R.) ảnh hiện thi khi thông báo
                // có thể add ảnh
                .setSound(sound)
                // độ ưu tiên
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setLargeIcon(bitmap)
//                .setColor()
                .build();
        NotificationManagerCompat  notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification);
// cách 2 ko cần check null
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if(notificationManager !=null) {
//            notificationManager.notify(getNotificationId(), notification);
//        }
    }
    // gửi với title dài
    private void sendNofication3() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Notification notification = new NotificationCompat.Builder(this, Application.CHANNEL_ID)
                .setContentTitle(TITLE_PUSH_NOFICATION)
                .setContentText(TITLE_PUSH_Content)

                //.setSmallIcon(R.) ảnh hiện thi khi thông báo
                .setStyle(new NotificationCompat.BigTextStyle().bigText(TITLE_PUSH_Content))// chữ contten có thể với chữ dài còn title nên để ngắn
                // có thể add ảnh
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setLargeIcon(bitmap)
//                .setColor()
                .build();
        NotificationManagerCompat  notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification);
// cách 2 ko cần check null
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if(notificationManager !=null) {
//            notificationManager.notify(getNotificationId(), notification);
//        }
    }
// hiện thi nhiêu nitification khi click nhiều
    private int getNotificationId() {
        return (int) new Date().getTime();
    }
// gửi theo custom tự thiết kế
    private void sendCustomNotification(){
        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tiengdong_com);
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.custom);
        notificationLayout.setTextViewText(R.id.tv_title_custom, "Title Notification");
        notificationLayout.setTextViewText(R.id.tv_message_custom, "Content Notification");
        // khia baos thowif gian
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String strDate = sdf.format(new Date());

        notificationLayout.setTextViewText(R.id.tv_time_custom, strDate);
        Notification notification = new NotificationCompat.Builder(this, Application.CHANNEL_ID_2)
                //.setSmallIcon(R.) ảnh hiện thi khi thông báo
                // có thể add ảnh
                .setSound(sound)
                .setCustomContentView(notificationLayout)// nhows
                // độ ưu tiên
//                .setColor()
                .build();
        NotificationManagerCompat  notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification);

    }
}