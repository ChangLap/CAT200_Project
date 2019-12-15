package com.example.cat200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class bookingDone extends AppCompatActivity {

    Timer timer;
    TextView showcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_done);


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(bookingDone.this, mainMenu.class);
                startActivity(intent);
            }
        },2000);

        showcode=(TextView) findViewById(R.id.booking_code);
        Bundle extra = getIntent().getExtras();
        if(extra!=null){
            showcode.setText(extra.getString("KEY"));
        }
    }
}
