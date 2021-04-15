package com.test.mvvmretro.alarmmanager;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.test.mvvmretro.R;

import java.util.Calendar;

import static com.test.mvvmretro.App.getContext;

public class DailyNotifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_notify);


        TextView tvSelectedTime = findViewById(R.id.tv_selected_time);
        Button btnTimerPicker = findViewById(R.id.btn_time_picker);
        Button btnCancelAlarm = findViewById(R.id.btn_cancel);
        Button btnCustomToast = findViewById(R.id.btn_custom_toast);
        btnCustomToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(DailyNotifyActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.custom_toast, null);

                Toast customToast = new Toast(DailyNotifyActivity.this);
                customToast.setGravity(Gravity.LEFT, -600, 0);
                customToast.setDuration(Toast.LENGTH_LONG);
                customToast.setView(view);
                customToast.show();

            }
        });


        btnCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(getContext(), NotificationReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 1, intent, 0);
                alarmManager.cancel(pendingIntent);
                tvSelectedTime.setText("Alarm Canceled");
            }
        });
        btnTimerPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePick = new TimePickerFragment(tvSelectedTime);
                timePick.show(getSupportFragmentManager(), "Time Picker");
            }
        });
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        private TextView tvTimeSelected;

        public TimePickerFragment(TextView tvTimeSelected) {
            this.tvTimeSelected = tvTimeSelected;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            c.set(Calendar.SECOND, 0);

            tvTimeSelected.setText("Selected Time: " + hourOfDay + ":" + minute);
            AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(getContext(), NotificationReceiver.class);
            //requestcode must be unique for each pending intent, since we have only one pi here let say it be 1
            //flag is for different behaviour of pending intent
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 1, intent, 0);
            // if we choose time in past if fire immediately
            //our calendar picked time plus the current time
            if (c.before(Calendar.getInstance())) {
                c.add(Calendar.DATE, 1);
            }
            //wake up device if it goes off
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

            tvTimeSelected.setText("Selected Time: " + hourOfDay + ":" + minute);
        }
    }
}