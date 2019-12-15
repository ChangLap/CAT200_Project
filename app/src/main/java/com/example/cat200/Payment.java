package com.example.cat200;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
    double totalamountdue;
    double balance;


    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference rootReference = firebaseDatabase.getReference();
    DatabaseReference currentReference;
    DatabaseReference walletReference;
    DatabaseReference plateReference;
    DatabaseReference costReference;
    String carPlate;
    String sBalance;
    String bookingPlate;
    int cost = 0;
    int current;
    long max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        currentReference = rootReference.child("current");
        currentReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                current = Integer.parseInt(dataSnapshot.getValue().toString());

                walletReference = rootReference.child("Login Details").child("user" + current).child("ewallet");
                walletReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        sBalance = dataSnapshot.getValue().toString();
                        Toast.makeText(Payment.this, "Balance" + sBalance, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                plateReference = rootReference.child("Login Details").child("user" + current).child("carPlate");
                plateReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        carPlate = dataSnapshot.getValue().toString();
                        Toast.makeText(Payment.this, "Carplate no " + carPlate, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Toast.makeText(Payment.this, "Line: " + current, Toast.LENGTH_LONG).show();

                costReference = rootReference.child("Booking History");
                costReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists())
                            max = dataSnapshot.getChildrenCount();

                        for (int i = 0; i < max; i++) {
                            bookingPlate = dataSnapshot.child("" + i).child("carPlate").getValue().toString();
                            if (carPlate.equals(bookingPlate))
                                cost = cost + Integer.parseInt(dataSnapshot.child("" + i).child("charge").getValue().toString());

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Toast.makeText(Payment.this, "" + current + " " + carPlate + " " + bookingPlate + " " + cost, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//        amountdue = (TextView) findViewById(R.id.tvAmountDue);
//        String s = String.valueOf(totalamountdue);
//
//        amountdue.setText("RM " + s);


        @Override
        protected void onStart(){
            super.onStart();


//        buttonPaynow = findViewById(R.id.bPay);
//        buttonPaynow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (balance>totalamountdue) {
//                    balance=balance-totalamountdue;
//                    final DatabaseReference updatewallet = FirebaseDatabase.getInstance().getReference().child("Login Details").child("user"+currentuser);
//                    updatewallet.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            balance = (double) dataSnapshot.getValue();
//                            updatewallet.setValue(balance);
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
////                    take.setEwallet(balance);
////
////                    .child("user" + currentuser).setValue(take);
//
//                    Intent success = new Intent(Payment.this, PaymentSuccess.class);
//                    startActivity(success);
//                } else {
//                    Intent fail = new Intent(Payment.this, PaymentFail.class);
//                    startActivity(fail);
//                }
//            }
//        });
        }
    }
