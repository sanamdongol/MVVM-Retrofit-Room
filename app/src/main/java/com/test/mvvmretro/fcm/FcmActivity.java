package com.test.mvvmretro.fcm;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.test.mvvmretro.R;

public class FcmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcm);

        FirebaseMessaging.getInstance().unsubscribeFromTopic("game")
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Toast.makeText(FcmActivity.this, "Game topic unsub", Toast.LENGTH_SHORT).show();
                    }
                });

    }


    public void cakeTopicSubscribe(View view) {
        FirebaseMessaging.getInstance().subscribeToTopic("cake")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(FcmActivity.this, "Cake Subscribed Failed", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(FcmActivity.this, "Cake Subscribed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void eggsTopicSubscription(View view) {
        FirebaseMessaging.getInstance().subscribeToTopic("eggs")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(FcmActivity.this, "Eggs Subscribed Faile", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(FcmActivity.this, "Eggs Subscribed Faile", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void gameTopicSubscription(View view) {
        FirebaseMessaging.getInstance().subscribeToTopic("game")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(FcmActivity.this, "game Subscribed Failed", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(FcmActivity.this, "game Subscribed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}