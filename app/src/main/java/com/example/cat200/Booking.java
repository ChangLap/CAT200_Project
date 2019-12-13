package com.example.cat200;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Booking extends AppCompatActivity {


//    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//            .setAction("Action", null).show();

    EditText edit_Date, edit_Start, edit_End;
    TextView view_Notice, view_Parking;
    Button button_Book;
    DatabaseReference databaseReference;
    bookingHistory bookingHistory;

    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;
    long folder = 0;
    int current;
    int carPlate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        edit_Start = (EditText) findViewById(R.id.etStart);
        edit_End = (EditText) findViewById(R.id.etEnd);
        button_Book = (Button) findViewById(R.id.btnBook);
        view_Notice = (TextView) findViewById(R.id.tvNotice);
        view_Parking = (TextView) findViewById(R.id.tvParking);

        databaseReference = FirebaseDatabase.getInstance().getReference();

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

                        edit_End.setText(String.format("%02d:%02d", hourOfDay, minute));
//                    edit_End.setText(hourOfDay + ":" + minute + amPm);
                    }


                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });


    button_Book.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists())
                        folder = dataSnapshot.getChildrenCount();

                    int current = Integer.parseInt(dataSnapshot.child("current").getValue().toString());
                    String carPlate = dataSnapshot.child("Login Details").child("user" + current).child("carPlate").getValue().toString();



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            bookingHistory.setCarPlate(carPlate);


        }
    });
    }
}
