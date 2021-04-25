package com.test.mvvmretro.deeplink;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.test.mvvmretro.R;

public class DeepLinkActivity extends AppCompatActivity {

    TextView tvData;
    private LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);

        tvData = findViewById(R.id.tv_data);
        linear = findViewById(R.id.linear);

        Uri uri = getIntent().getData();

        if (uri != null) {
            String data = uri.toString();
            tvData.setText(data);
        }

        //TODO: dynamic link generation

        /**
         * view =textview
         * parent = null
         * attachToRoot/layoutParams = LinearLayout
         */
        View view = LayoutInflater.from(this).inflate(R.layout.fancy_row, linear, false);
        TextView tv = view.findViewById(R.id.tv);
        tv.setGravity(Gravity.BOTTOM);
        linear.setBackgroundColor(Color.parseColor("#bdbdbd"));
        linear.addView(view);


    }
}