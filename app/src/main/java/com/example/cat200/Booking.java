package com.example.cat200;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Booking extends AppCompatActivity {


//    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//            .setAction("Action", null).show();

    EditText edit_Date, edit_Start, edit_End;
    TextView view_Notice, view_Parking;

    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        edit_Start = (EditText) findViewById(R.id.etStart);
        edit_Start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(Booking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay < 12)
                            amPm = "AM";
                        else
                            amPm = "PM";

                        edit_Start.setText(String.format("%02d:%02d", hourOfDay, minute) + amPm);
//                    edit_End.setText(hourOfDay + ":" + minute + amPm);
                    }


                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

        edit_End = (EditText) findViewById(R.id.etEnd);
        edit_End.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(Booking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay < 12)
                            amPm = "AM";
                        else
                            amPm = "PM";

                        edit_End.setText(String.format("%02d%02d", hourOfDay, minute));
//                    edit_End.setText(hourOfDay + ":" + minute + amPm);
                    }


                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

    }
}
