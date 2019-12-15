package com.example.cat200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.os.Bundle;
import android.widget.ImageView;

public class Ewallet extends AppCompatActivity {

    ImageView image_ewallet;
    ImageView image_payment;
    ImageView image_topup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ewallet);

        image_payment = (ImageView) findViewById(R.id.ivPayment);
        image_topup = (ImageView) findViewById(R.id.ivTopup);

        image_payment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent change = new Intent(Ewallet.this, Payment.class);
                startActivity(change);

            }
        });

        image_topup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent tu = new Intent(Ewallet.this, TopUp.class);
                startActivity(tu);
            }
        });
    }

}
