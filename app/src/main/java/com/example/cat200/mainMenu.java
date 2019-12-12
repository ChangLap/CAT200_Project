package com.example.cat200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button booking = (Button) findViewById(R.id.bookingButton);
        booking.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bookingScene();
            }
        });

        Button report = (Button) findViewById(R.id.reportButton);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportScene();
            }
        });
    }

    public void bookingScene() {
        Intent change = new Intent(this, Booking.class);
        startActivity(change);
    }

    public void reportScene() {
        Intent change = new Intent(this, Report.class);
        startActivity(change);
    }
}
