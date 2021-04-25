package com.test.mvvmretro.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.test.mvvmretro.R;

public class FcmService extends FirebaseMessagingService {

    private static final String CHANNEL_ID_A = "1";

    private static final String TAG = "FcmService";

    @Override
    public void onNewToken(@NonNull String s) {
        Log.e("fcmid", s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        String topics = remoteMessage.getFrom();

        if (topics.equalsIgnoreCase("/topics/cake")) {
            showA("Cake", "This is cake");
        } else
            showA("Eggs", "This is egss");


    }

    private void showA(String title, String content) {
        Log.e("showa", "showa");

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID_A)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentText(title)
                        .setContentTitle(content);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            String channelName = "Channel A";
            String channelDescription = "This is channel A descriptoin";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_A, channelName, importance);
            channel.setDescription(channelDescription);
            manager.createNotificationChannel(channel);
        }

        manager.notify((int) System.currentTimeMillis(), builder.build());
        // manager.notify(1, builder.build());

    }
}
