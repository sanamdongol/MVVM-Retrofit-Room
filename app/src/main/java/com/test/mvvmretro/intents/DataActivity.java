package com.test.mvvmretro.intents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.test.mvvmretro.R;

import static com.test.mvvmretro.intents.MainActivity.RC_BACK_ACT;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


    }

    public void goBackWithData(View view) {
        String message = "Thank you for coming here";
        Intent intent = new Intent();
        intent.putExtra("message", message);
        setResult(RC_BACK_ACT, intent);
        finish();
    }
}