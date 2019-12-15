package com.example.cat200;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Payment extends AppCompatActivity {

    TextView amountdue;
    Button buttonPaynow;
    int balance;


    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference rootReference = firebaseDatabase.getReference();
    DatabaseReference currentReference;
    DatabaseReference readReference;
    DatabaseReference plateReference;
    DatabaseReference costReference;
    DatabaseReference updatewallet;
    boolean flag;
    String carPlate;
    String bookingPlate;
    int cost = 0;
    int current;
    long max;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        amountdue = (TextView) findViewById(R.id.tvAmountDue);
        buttonPaynow = (Button) findViewById(R.id.bPay);
        buttonPaynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(balance > cost ){
                    Intent change = new Intent(Payment.this, PaymentSuccess.class);
                    startActivity(change);
                }
                else{
                    Intent change = new Intent(Payment.this, PaymentFail.class);
                    startActivity(change);
                }

                //Toast.makeText(Payment.this, current + " " + carPlate + " " + balance+ " " + cost, Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        currentReference = rootReference.child("current");
        currentReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                current = Integer.parseInt(dataSnapshot.getValue().toString());

                readReference = rootReference.child("Login Details").child("user" + current);
                readReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        balance = Integer.parseInt(dataSnapshot.child("ewallet").getValue().toString());
                        carPlate = dataSnapshot.child("carPlate").getValue().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                costReference = rootReference.child("Booking History");
                costReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists())
                            max = dataSnapshot.getChildrenCount();

                        for (int i = 0; i < max; i++) {
                            bookingPlate = dataSnapshot.child("" + i).child("carPlate").getValue().toString();
                            flag = Boolean.valueOf(dataSnapshot.child("" + i).child("flag").getValue().toString());
                            if (carPlate.equals(bookingPlate) && !flag)
                                cost = cost + Integer.parseInt(dataSnapshot.child("" + i).child("charge").getValue().toString());

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
<<<<<<< HEAD
//                Toast.makeText(Payment.this, "" + current + " " + carPlate + " " + bookingPlate + " " + cost, Toast.LENGTH_LONG).show();


=======
>>>>>>> 8467041ed178ef1b518ab0946fade311bcd88a0a
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }
}