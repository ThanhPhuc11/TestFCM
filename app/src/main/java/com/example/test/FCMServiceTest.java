package com.example.test;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class FCMServiceTest extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
//        if(remoteMessage.getData().isEmpty()) {
//            showNoti(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
//        } else {
//            showNoti(remoteMessage.getData());
//        }

        if(remoteMessage.getNotification()!=null){
            hienThiThongBao(remoteMessage.getNotification().getBody());
        }else {
            hienThiThongBao(remoteMessage.getData().get("bodyy"), remoteMessage.getData().get("titlee"));
        }
    }

    private void hienThiThongBao(String body,String title) {
        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(sound)
                .setContentIntent(pendingIntent);
        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
    private void hienThiThongBao(String body) {
        hienThiThongBao(body,"google");
    }

    private void showNoti(Map<String, String> data) {
        String title = data.get("tai to");
        String body = data.get("bo di");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "com.example.test";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Noti",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("CC");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("InfoCC");

        notificationManager.notify(new Random().nextInt(), notificationBuilder.build());
    }

//    private void showNoti(String title, String body){
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        String NOTIFICATION_CHANNEL_ID = "com.example.test";
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Noti",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            notificationChannel.setDescription("CC");
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
//        notificationBuilder.setAutoCancel(true)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setWhen(System.currentTimeMillis())
//                .setContentTitle(title)
//                .setContentText(body)
//                .setContentInfo("InfoCC");
//
//        notificationManager.notify(new Random().nextInt(), notificationBuilder.build());
//
//    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("AAA", s);
    }
}
