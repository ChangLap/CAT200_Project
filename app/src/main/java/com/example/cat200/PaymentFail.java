package com.example.cat200;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PaymentFail extends AppCompatActivity {

    TextView showbalance;
    DatabaseReference databaseReference;
    Button to_topup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_fail);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Login Details").child("user0");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sBalance = dataSnapshot.child("ewallet").getValue().toString();
                showbalance = (TextView) findViewById(R.id.balance_payfail);
                showbalance.setText("Current balance: RM "+ sBalance);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        to_topup = (Button) findViewById(R.id.bPayFail_topup);
//        to_topup.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent change = new Intent(PaymentFail.this, Topup.class);
//                startActivity(change);
//            }
//        });
    }
}
