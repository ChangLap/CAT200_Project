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
    double balance;


    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference rootReference = firebaseDatabase.getReference();
    DatabaseReference currentReference;
    DatabaseReference walletReference;
    DatabaseReference plateReference;
    DatabaseReference costReference;
    DatabaseReference updatewallet;
    boolean flag;
    boolean paySuccess;
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
        buttonPaynow = findViewById(R.id.bPay);
        buttonPaynow.setOnClickListener(new View.OnClickListener() {
//            @Override
            public void onClick(View v) {
                if (balance>cost) {
                    Intent success = new Intent(Payment.this, PaymentSuccess.class);
                    startActivity(success);
                    balance = balance - cost;
                    updatewallet = rootReference.child("Login Details").child("user" + current);
                    updatewallet.child("ewallet").setValue(balance);
//                    Toast.makeText(Payment.this, "Balance" + sBalance, Toast.LENGTH_LONG).show();
                } else {
                    Intent fail = new Intent(Payment.this, PaymentFail.class);
                    startActivity(fail);
                }
            }
        });

//        Toast.makeText(Payment.this, "" + current + " " + carPlate + " " + bookingPlate + " " + cost, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();


            currentReference = rootReference.child("current");
            currentReference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                current = Integer.parseInt(dataSnapshot.getValue().toString());

                walletReference = rootReference.child("Login Details").child("user" + current).child("ewallet");
                walletReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        sBalance = dataSnapshot.getValue().toString();
//                        Toast.makeText(Payment.this, "Balance" + sBalance, Toast.LENGTH_LONG).show();
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
                            flag = Boolean.valueOf(dataSnapshot.child("" + i).child("flag").getValue().toString());
                            if (carPlate.equals(bookingPlate) && !flag)
                                cost = cost + Integer.parseInt(dataSnapshot.child("" + i).child("charge").getValue().toString());

                        }
                        Toast.makeText(Payment.this, "" + current + " " + carPlate + " " + bookingPlate + " " + cost, Toast.LENGTH_LONG).show();

                        amountdue = (TextView) findViewById(R.id.tvAmountDue);
                        String s = String.valueOf(cost);
                        amountdue.setText("RM " + s);

                        balance = Double.valueOf(sBalance);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
<<<<<<< HEAD
//                Toast.makeText(Payment.this, "" + current + " " + carPlate + " " + bookingPlate + " " + cost, Toast.LENGTH_LONG).show();
=======
>>>>>>> e409e34f3b44a4a15481c35d12ddc9f603b83bc2

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
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

