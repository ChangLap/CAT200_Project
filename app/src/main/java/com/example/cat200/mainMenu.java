package com.example.cat200;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class mainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button booking = (Button) findViewById(R.id.bookingButton);
        booking.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mappingScene();
            }
        });

        Button report = (Button) findViewById(R.id.reportButton);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportScene();
            }
        });

        Button wallet = (Button) findViewById(R.id.walletButton);
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                walletScene();
            }
        });

        Button record = (Button) findViewById(R.id.recordButton);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordScene();
            }
        });
    }

    public void mappingScene() {
        Intent change = new Intent(this, Mapping.class);
        startActivity(change);
    }

    public void reportScene() {
        Intent change = new Intent(this, Report.class);
        startActivity(change);
    }

    public void walletScene(){
        Intent change = new Intent(this, Ewallet.class);
        startActivity(change);
    }

    public void recordScene(){
        Intent change = new Intent(this, BookingRecord.class);
        startActivity(change);
    }

    public void qrScene(){
        Intent change = new Intent(this, QRCode.class);
        startActivity(change);
    }
}
