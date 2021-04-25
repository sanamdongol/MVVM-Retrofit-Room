package com.test.mvvmretro.chips;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.test.mvvmretro.R;

public class ChipsActivity extends AppCompatActivity {
    Chip chip;

    String[] sports = {"Ice Hockey", "Skate", "Sking", "Moto Race",
            "Skateboard", "Gymnastic", "Boxing", "Dodge Ball"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chips);

        chip = findViewById(R.id.chip);
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChipsActivity.this, "Chip Click", Toast.LENGTH_SHORT).show();
            }
        });

        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChipsActivity.this, "Close icon Click", Toast.LENGTH_SHORT).show();
            }
        });


        ChipGroup chipGroup = findViewById(R.id.chip_group);

        for (int i = 0; i < sports.length; i++) {
            Chip chip = new Chip(this);
            chip.setText(sports[i]);
            chip.setTag(sports[i]);
            chipGroup.addView(chip);

            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //String tag = view.getTag().toString();
                    String tag = ((Chip) view).getText().toString();
                    Toast.makeText(ChipsActivity.this, tag
                            , Toast.LENGTH_SHORT).show();
                    chip.setChecked(true);
                }
            });
        }

    }
}