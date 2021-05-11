package com.test.mvvmretro.snackbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.test.mvvmretro.R;

public class SnackActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack);
        constraintLayout = findViewById(R.id.rootLayout);
    }

    public void showNormalSnackbar(View view) {
        Toast.makeText(this, "dsfsdf", Toast.LENGTH_SHORT).show();
        Snackbar.make(constraintLayout, "Delete email", Snackbar.LENGTH_LONG).show();
    }

    public void showSnackWithAction(View view) {
        Snackbar snackbar = Snackbar.make(constraintLayout, "Delete email", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(constraintLayout, "Email Recovered", Snackbar.LENGTH_LONG).show();
            }
        });
        snackbar.show();
    }

    public void showCustomSnackbar(View view) {

        Snackbar snackbar = Snackbar.make(constraintLayout, "", Snackbar.LENGTH_LONG);
        View customSnack = getLayoutInflater().inflate(R.layout.custom_snackbar, null);

        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.removeAllViews();
        snackbarLayout.addView(customSnack);
        snackbar.show();
    }
}