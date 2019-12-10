package com.example.cat200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    EditText edit_Email, edit_Password;
    TextView text_Register;
    Button button_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_Email = (EditText) findViewById(R.id.et_email);
        edit_Password = (EditText) findViewById(R.id.et_password);
        text_Register = (TextView) findViewById(R.id.tv_register);
        button_Login = (Button) findViewById(R.id.btn_login);

        text_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerScene();
            }
        });

        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainScene();
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
