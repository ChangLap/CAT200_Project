package com.example.cat200;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    EditText etEmail, etPassword, etCarPlate;
    Button button_register;
    DatabaseReference databaseReference;
    long folder = 0;
    userDetail detail = new userDetail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etCarPlate = (EditText) findViewById(R.id.etCarPlate);
        button_register = (Button) findViewById(R.id.btnSave);

        //content of child refer to the tree
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Login Details");

        //auto increment of folder name
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    folder = (dataSnapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail.setEmail(etEmail.getText().toString().trim());
                detail.setPassword(etPassword.getText().toString().trim());
                detail.setCarPlate(etCarPlate.getText().toString().trim());
                detail.setEwallet(0);
                //auto increment of folder's name
                databaseReference.child("user" + folder).setValue(detail);

                //display toast
                Toast.makeText(Register.this, "Data pushed.", Toast.LENGTH_LONG).show();

                Intent change = new Intent(Register.this, Login.class);
                startActivity(change);
            }
        });
    }
}
