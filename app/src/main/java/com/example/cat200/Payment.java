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

//    TextView amountdue;
//    Button buttonPaynow;
//    double totalamountdue = 3.5;
//    double balance;


    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference rootReference = firebaseDatabase.getReference();
    DatabaseReference currentReference;
    DatabaseReference walletReference;
    String sBalance;
    int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

//        amountdue = (TextView) findViewById(R.id.tvAmountDue);
//        String s = String.valueOf(totalamountdue);
//
//        amountdue.setText("RM " + s);

        Toast.makeText(Payment.this, "Line: " + current, Toast.LENGTH_LONG).show();
//        Toast.makeText(Payment.this, "Balance" + sBalance, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStart () {
        super.onStart();

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
