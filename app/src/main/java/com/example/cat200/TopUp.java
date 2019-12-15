package com.example.cat200;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TopUp extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference rootReference = firebaseDatabase.getReference();
    DatabaseReference ewalletReference;
    DatabaseReference nowReference;
    DatabaseReference setwalletReference;
    TextView showWallet;
    EditText keyinAmount;
    Button comfirmTU;
    int now, add, walletamount;
    String walletString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);


        keyinAmount = (EditText) findViewById(R.id.keyinTopUP);

        nowReference = rootReference.child("current");
        nowReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                now = Integer.parseInt(dataSnapshot.getValue().toString());

                ewalletReference = rootReference.child("Login Details").child("user"+now).child("ewallet");
                ewalletReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        showWallet = (TextView) findViewById(R.id.b_showBalance);
                        showWallet.setText("RM "+dataSnapshot.getValue().toString());

                        walletamount = Integer.parseInt(dataSnapshot.getValue().toString());
                        comfirmTU = (Button) findViewById (R.id.bcomfirmTopup);
                        comfirmTU.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                add = Integer.parseInt(keyinAmount.getText().toString());
                                walletamount+=add;
                                setwalletReference = rootReference.child("Login Details").child("user"+now);
                                setwalletReference.child("ewallet").setValue(walletamount);
                                add=0;
                                Toast.makeText(TopUp.this, "Ewallet" + String.valueOf(walletamount), Toast.LENGTH_LONG).show();
                            }
                        });
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
