package com.test.mvvmretro.intents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import com.test.mvvmretro.R;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    private static final int RC_CAMERA = 99;
    public static final int RC_BACK_ACT = 1;
    EditText etWebAddress;
    private ImageView img;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        etWebAddress = findViewById(R.id.et_website);
        img = findViewById(R.id.img);
        tvResult = findViewById(R.id.tv_get_result);
    }

    public void openWebsite(View view) {
        Toast.makeText(this, "Please wait", Toast.LENGTH_SHORT).show();
        String webUrl = etWebAddress.getText().toString();
        Uri uri = Uri.parse(webUrl);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Can't find application to run", Toast.LENGTH_SHORT).show();
        }
    }

    public void openLocation(View view) {

        String loc = "Kathmandu";
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Can't find application to run", Toast.LENGTH_SHORT).show();
        }
    }

    public void shareMyText(View view) {
        String txt = "I am to be shared";
        // String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("TITLE")
                .setText(txt)
                .startChooser();
    }

    public void shareImage(View view) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.brave);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore
                .Images
                .Media
                .insertImage(getContentResolver(),
                        bitmap, "Image Title", "Desc");
        Uri uri = Uri.parse(path);
        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "Select App to share this image"));
    }

    public void captureImage(View view) {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, RC_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_CAMERA) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap bitmap = (Bitmap) bundle.get("data");
                img.setImageBitmap(bitmap);
            }
            Toast.makeText(this, "Back From Camera", Toast.LENGTH_SHORT).show();
        } else if (requestCode == RC_BACK_ACT) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String txt = data.getStringExtra("message");
                tvResult.setText(txt);
            }
        }
    }

    public void changeActivity(View view) {
        Intent intent = new Intent(this, DataActivity.class);
        startActivityForResult(intent, RC_BACK_ACT);
    }
}