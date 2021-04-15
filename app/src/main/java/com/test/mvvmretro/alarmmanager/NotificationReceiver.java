package com.test.mvvmretro.alarmmanager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.test.mvvmretro.R;

public class NotificationReceiver extends BroadcastReceiver {
    private static final String CHANNEL_A_ID = "1";

    @Override
    public void onReceive(Context context, Intent intent) {
        sendNotification(context);
    }

    private void sendNotification(Context context) {

        Intent intent = new Intent(context, DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_A_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("App Name")
                .setContentTitle("Content Title")
                .setContentText("Content Text")
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "Channel A";
            String description = "App Name";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_A_ID, channelName, importance);
            channel.setDescription(description);
            mNotificationManager.createNotificationChannel(channel);
        }
        mNotificationManager.notify((int) System.currentTimeMillis(), mBuilder.build());
    }
}
