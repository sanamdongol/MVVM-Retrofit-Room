package com.test.mvvmretro.pdfdownload;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.test.mvvmretro.R;


public class WebActivity extends AppCompatActivity {

    WebView webView;
    private String pdfUrl = "https://www.nrb.org.np/contents/uploads/2021/03/Deputy-Governors-Oath-final.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + pdfUrl);
    }
}