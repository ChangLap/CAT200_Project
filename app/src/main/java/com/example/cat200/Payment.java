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
    String plate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        buttonPaynow = (Button) findViewById(R.id.bPay);
        updatewallet = FirebaseDatabase.getInstance().getReference();
        buttonPaynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Payment.this, current + " " + carPlate + " " + balance+ " " + cost, Toast.LENGTH_LONG).show();
                if(balance > cost ){
                    balance = balance - cost;

                    costReference = FirebaseDatabase.getInstance().getReference().child("Booking History");
                    costReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (int i = 0; i <= max; i++) {
                                bookingPlate = dataSnapshot.child("" + i).child("carPlate").getValue().toString();
                                flag = Boolean.valueOf(dataSnapshot.child("" + i).child("flag").getValue().toString());
                                if (carPlate.equals(bookingPlate) && !flag){
                                    flag = true;
                                    updatewallet.child("Booking History").child(""+i).child("flag").setValue(flag);
                                }

                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });

                    updatewallet.child("Login Details").child("user"+ current).child("ewallet").setValue(balance);

                    Intent change = new Intent(Payment.this, PaymentSuccess.class);
                    startActivity(change);
                }
                else{
                    Intent change = new Intent(Payment.this, PaymentFail.class);
                    startActivity(change);
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        currentReference = FirebaseDatabase.getInstance().getReference().child("current");
        currentReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                current = Integer.parseInt(dataSnapshot.getValue().toString());

                readReference = FirebaseDatabase.getInstance().getReference().child("Login Details").child("user" + current);
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

                costReference = FirebaseDatabase.getInstance().getReference().child("Booking History");
                costReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists())
                            max = dataSnapshot.getChildrenCount();
                        Toast.makeText(Payment.this,"max: "+ max , Toast.LENGTH_LONG).show();
                        for (int i = 0; i < max; i++) {
                            bookingPlate = dataSnapshot.child("" + i).child("carPlate").getValue().toString();
                            flag = Boolean.valueOf(dataSnapshot.child("" + i).child("flag").getValue().toString());
                            if (carPlate.equals(bookingPlate) && !flag)
                                cost = cost + Integer.parseInt(dataSnapshot.child("" + i).child("charge").getValue().toString());

                        }
                        amountdue = (TextView) findViewById(R.id.tvAmountDue);
                        amountdue.setText("RM"+ String.valueOf(cost));

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

    }

}