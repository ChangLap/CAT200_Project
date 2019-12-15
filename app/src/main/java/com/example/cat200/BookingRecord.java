package com.example.cat200;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookingRecord extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    DatabaseReference databaseReference;
    DatabaseReference readReference;
    DatabaseReference currentReference;
    DatabaseReference plateReference;

    int current;
    String carPlate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_record);

        databaseReference = FirebaseDatabase.getInstance().getReference("Booking History");

        listView = (ListView) findViewById(R.id.listRecord);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);

        currentReference = FirebaseDatabase.getInstance().getReference().child("current");
        currentReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                current = Integer.parseInt(dataSnapshot.getValue().toString());
                Toast.makeText(BookingRecord.this, "Write no " + current, Toast.LENGTH_LONG).show();

                plateReference = FirebaseDatabase.getInstance().getReference().child("Login Details").child("user" + current).child("carPlate");
                plateReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        carPlate = dataSnapshot.getValue().toString();
                        Toast.makeText(BookingRecord.this, "Carplate no " + carPlate, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                databaseReference = FirebaseDatabase.getInstance().getReference("Booking History");
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        String value = dataSnapshot.getValue(bookingHistory.class).toString(carPlate);
                        Toast.makeText(BookingRecord.this, "plate" + carPlate, Toast.LENGTH_LONG).show();
                        arrayList.add(value);
                        arrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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

    @Override
    protected void onStart() {
        super.onStart();


    }
}
