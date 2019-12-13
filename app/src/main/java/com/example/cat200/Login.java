package com.example.cat200;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Login extends AppCompatActivity {

    EditText edit_Email, edit_Password;
    TextView text_Register;
    Button button_Login;
    DatabaseReference databaseReference;
    long max = 0;
    int current = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_Email = (EditText) findViewById(R.id.et_email);
        edit_Password = (EditText) findViewById(R.id.et_password);
        text_Register = (TextView) findViewById(R.id.tv_register);
        button_Login = (Button) findViewById(R.id.btn_login);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Login Details");

        button_Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                databaseReference.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                            max = dataSnapshot.getChildrenCount();

                        String email;
                        String password;
                        String carplate;

                        for( current = 0; current < max; current++){
                            email = dataSnapshot.child("user" + current).child("email").getValue().toString();
                            if((edit_Email.getText().toString()).equals(email))
                                break;
                        }

                        Toast.makeText(Login.this, "Success Reading. ", Toast.LENGTH_SHORT).show();

                        password = dataSnapshot.child("user" + current).child("password").getValue().toString();
                        if ( password.equals(edit_Password.getText().toString()))
                            mainScene();
                        else
                            Toast.makeText(Login.this, "Wrong password. ", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        text_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerScene();
            }
        });

    }

    public void mainScene() {
        Intent change = new Intent(Login.this, mainMenu.class);
        startActivity(change);
    }

    public void registerScene() {
        Intent change = new Intent(Login.this, Register.class);
        startActivity(change);
    }

}
