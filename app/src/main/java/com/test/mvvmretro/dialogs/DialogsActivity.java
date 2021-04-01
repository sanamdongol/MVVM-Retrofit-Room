package com.test.mvvmretro.dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.test.mvvmretro.R;

public class DialogsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);


        Button btnShowDefaultDialog = findViewById(R.id.btn_default);
        Button btnShowCustomDialog = findViewById(R.id.btn_custom);
        btnShowDefaultDialog.setOnClickListener(this);
        btnShowCustomDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_default:
                showDefaultDialog();
                break;

            case R.id.btn_custom:
                showCustomDialog();
                break;
        }

    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DialogsActivity.this);

        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_dialog, null);
        Button btnGood = view.findViewById(R.id.btn_good);
        Button btnBad = view.findViewById(R.id.btn_bad);
        Button btnNotGood = view.findViewById(R.id.btn_not_good);

        btnGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogsActivity.this, "This car is good", Toast.LENGTH_SHORT).show();
            }
        });

        btnNotGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogsActivity.this, "This car is Not good", Toast.LENGTH_SHORT).show();
            }
        });

        btnBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogsActivity.this, "This car is bad", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setView(view);
        builder.show();

    }

    private void showDefaultDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DialogsActivity.this);
        builder.setTitle("Title");
        builder.setMessage("This is Message");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNeutralButton("May be", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }
}