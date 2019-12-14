package com.example.cat200;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ApplicationErrorReport;
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

public class Report extends AppCompatActivity {


    EditText etReport;
    Button button_submit;
    DatabaseReference databaseReference;
    long folder = 0;
    private String reportContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        etReport = (EditText) findViewById(R.id.etReport);
        button_submit = (Button) findViewById(R.id.btnSubmit);

        //content of child refer to the tree
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Report");


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


        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportContent = etReport.getText().toString().trim();

                //auto increment of folder's name
                databaseReference.child("user" + folder).setValue(reportContent);

                //display toast
                Toast.makeText(Report.this, "Data pushed.", Toast.LENGTH_LONG).show();

            }
        });
    }
}