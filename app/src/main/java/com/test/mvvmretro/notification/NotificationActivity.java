package com.test.mvvmretro.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.test.mvvmretro.R;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String CHANNEL_ID_A = "1";
    public static final String CHANNEL_ID_B = "2";
    public static final String CHANNEL_ID_C = "3";
    public static final String CHANNEL_ID_D = "4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Button btnA = findViewById(R.id.btn_notify_a);
        Button btnB = findViewById(R.id.btn_notify_b);
        Button btnC = findViewById(R.id.btn_notify_c);
        Button btnD = findViewById(R.id.btn_notify_d);
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);



    }


    private void showA() {

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID_A)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentText("This is content context")
                        .setContentTitle("This is content title");


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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_notify_a:
                showA();
                break;

            case R.id.btn_notify_b:
                showB();
                break;
            case R.id.btn_notify_c:
                showC();
                break;
            case R.id.btn_notify_d:
                showD();
                break;

        }
    }

    private void showD() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID_D)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Event tracker")
                .setTicker("Inbox Style Notification")
                .setContentText("Events received");
        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();

        String[] events = new String[6];
        events[0] = "1. Drone Smash Act";
        events[1] = "2. Dirt Bike Rally";
        events[2] = "3. Walnut";
        events[3] = "4 .Boxing Match";
        events[4] = "5 .Chilly Eating Competition";
        events[5] = "6 .Skateboard Stunt";
        // Sets a title for the Inbox in expanded layout
        inboxStyle.setBigContentTitle("Event tracker details:");

        // Moves events into the expanded layout
        for (int i = 0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }

        builder.setStyle(inboxStyle);
        inboxStyle.setBigContentTitle("Events This Week");

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            String channelName = "Channel Last";
            String channelDescription = "This is channel A descriptoin";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_D, channelName, importance);
            channel.setDescription(channelDescription);
            manager.createNotificationChannel(channel);
        }

        manager.notify((int) System.currentTimeMillis(), builder.build());
    }

    private void showC() {

        Bitmap largeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.honda);
        Bitmap carImg = BitmapFactory.decodeResource(this.getResources(), R.drawable.car);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID_C)
                        .setSmallIcon(R.drawable.car)
                        .setContentText("This is channel C")
                        .setSound(sound)
                        .setLargeIcon(carImg)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(getString(R.string.nrb_desc)))
                        .setContentTitle("This is content title BBBBBB");


        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            String channelName = "Channel C";
            String channelDescription = "This is channel A descriptoin";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_C, channelName, importance);
            channel.setDescription(channelDescription);
            manager.createNotificationChannel(channel);
        }

        manager.notify((int) System.currentTimeMillis(), builder.build());
    }

    private void showB() {

        Bitmap largeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.honda);
        Bitmap carImg = BitmapFactory.decodeResource(this.getResources(), R.drawable.car);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID_B)
                        .setSmallIcon(R.drawable.car)
                        .setContentText("This is channel B")
                        .setSound(sound)
                        .setLargeIcon(carImg)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(largeIcon))
                        .setContentTitle("This is content title BBBBBB");


        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            String channelName = "Channel A";
            String channelDescription = "This is channel A descriptoin";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_B, channelName, importance);
            channel.setDescription(channelDescription);
            manager.createNotificationChannel(channel);
        }

        manager.notify((int) System.currentTimeMillis(), builder.build());
    }
}