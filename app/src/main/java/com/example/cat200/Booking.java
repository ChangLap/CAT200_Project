package com.example.cat200;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Booking extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


//    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//            .setAction("Action", null).show();

    EditText edit_Date, edit_Start, edit_End;
    TextView view_Notice, view_Parking;
    Button button_Book;
    bookingHistory bookingHistory = new bookingHistory();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference rootReference = firebaseDatabase.getReference();
    DatabaseReference currentReference;
    DatabaseReference plateReference;
    DatabaseReference parkingReference;
    DatabaseReference userReference;
    int current;
    String carPlate;
    int random;
    String parking;
    long folder = 0;
    int charge = 0;

    TimePickerDialog timePickerDialog;
    Calendar calender1;
    int startHour;
    int startMinute;
    int endHour;
    int endMinute;
    String amPm;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        edit_Date = (EditText) findViewById(R.id.etDate);
        edit_Start = (EditText) findViewById(R.id.etStart);
        edit_End = (EditText) findViewById(R.id.etEnd);
        button_Book = (Button) findViewById(R.id.btnBook);
        view_Notice = (TextView) findViewById(R.id.tvNotice);
        view_Parking = (TextView) findViewById(R.id.tvParking);


        edit_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        edit_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calender1 = Calendar.getInstance();
                startHour = calender1.get(Calendar.HOUR_OF_DAY);
                startMinute = calender1.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(Booking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay < 12)
                            amPm = "AM";
                        else
                            amPm = "PM";

                        edit_Start.setText(String.format("%02d:%02d", hourOfDay, minute));
                        startHour = hourOfDay;
                        startMinute = minute;
//                    edit_End.setText(hourOfDay + ":" + minute + amPm);
                    }


                }, startHour, startMinute, false);

                timePickerDialog.show();
            }
        });

        edit_End.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calender1 = Calendar.getInstance();
                endHour = calender1.get(Calendar.HOUR_OF_DAY);
                endMinute = calender1.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(Booking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay < 12)
                            amPm = "AM";
                        else
                            amPm = "PM";

                        edit_End.setText(String.format("%02d:%02d", hourOfDay, minute));
                        endHour = hourOfDay;
                        endMinute = minute;
//                    edit_End.setText(hourOfDay + ":" + minute + amPm);
                    }


                }, endHour, endMinute, false);

                timePickerDialog.show();
            }
        });

        userReference = rootReference.child("Booking History");
        //increment of folders
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                folder = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        button_Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int startTime = startHour*60 + startMinute;
                int endTime = endHour*60 + endMinute;
                int duration = endTime - startTime;
                int i;
                for (i = 0; duration > i*60; i++)
                    charge = charge +2;

                Toast.makeText(Booking.this, ""+current+" "+carPlate+" "+parking, Toast.LENGTH_LONG).show();
                bookingHistory.setCarPlate(carPlate);
                bookingHistory.setDate(edit_Date.getText().toString().trim());
                bookingHistory.setStartTime(edit_Start.getText().toString().trim());
                bookingHistory.setEndTime(edit_End.getText().toString().trim());
                bookingHistory.setSlot(parking);
                bookingHistory.setCharge(charge);
                bookingHistory.setFlag(false);

                view_Notice.setText("Your current parking slot is ");
                view_Parking.setTextSize(20);
                view_Parking.setText(parking);

                userReference.child(""+folder).setValue(bookingHistory);

                Toast.makeText(Booking.this, "Data pushed.", Toast.LENGTH_LONG).show();

                mainScene();


            }
        });
    }

    //shows the dialog of date picking
    private void showDatePickerDialog () {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    //set date into editText field
    @Override
    public void onDateSet (DatePicker view,int year, int month, int dayOfMonth){
        String date = dayOfMonth + "/" + month + "/" + year;
        edit_Date.setText(date);
    }

    @Override
    protected void onStart () {
        super.onStart();

        currentReference = rootReference.child("current");
        currentReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                current = Integer.parseInt(dataSnapshot.getValue().toString());
                Toast.makeText(Booking.this, "Write no " + current, Toast.LENGTH_SHORT).show();

                plateReference = rootReference.child("Login Details").child("user" + current).child("carPlate");
                plateReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        carPlate = dataSnapshot.getValue().toString();
                        Toast.makeText(Booking.this, "Carplate no " + carPlate, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        random = new Random().nextInt(58) + 1;
        parkingReference = rootReference.child("Parking Details").child("slot" + random);
        parkingReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                parking = dataSnapshot.getValue().toString();
                Toast.makeText(Booking.this, "Slot no " + parking, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        String string_carplate=String.valueOf(carPlate);
        bookingHistory.setCarPlate(string_carplate);


    }

    //delay change scene
    public void mainScene (){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent (Booking.this, mainMenu.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
