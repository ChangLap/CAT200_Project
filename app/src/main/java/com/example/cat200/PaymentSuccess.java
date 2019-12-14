package com.example.cat200;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PaymentSuccess extends AppCompatActivity {

    TextView showSuccess;
    DatabaseReference writedatabase;
    Button gobackmainmenu;
    String currentuser,newbalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        writedatabase = FirebaseDatabase.getInstance().getReference().child("current");
        writedatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentuser = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        writedatabase = FirebaseDatabase.getInstance().getReference().child("Login Details").child("user"+currentuser);
        writedatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                newbalance = dataSnapshot.child("ewallet").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        showSuccess = (TextView) findViewById(R.id.tvSuccess_ewallet);
        showSuccess.setText("RM "+newbalance);

        gobackmainmenu = (Button) findViewById(R.id.b_afterSuccess);
        gobackmainmenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent change = new Intent(PaymentSuccess.this, mainMenu.class);
                startActivity(change);
            }
        });

    }
}
