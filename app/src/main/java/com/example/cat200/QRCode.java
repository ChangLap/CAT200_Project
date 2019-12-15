package com.example.cat200;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class QRCode extends AppCompatActivity {
    private ImageView ImageView;
    String text;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference rootData = firebaseDatabase.getReference();
    DatabaseReference now;
    DatabaseReference info;
    String sekarang;
    Button butt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

<<<<<<< HEAD

        final Context context = this;
=======
        etInput = findViewById(R.id.etInput);
        btnCreateQr = findViewById(R.id.btnCreate);
>>>>>>> 0b8b8102c66ec5bbd1353cb9f8bc9aa579d0becc
        ImageView = findViewById(R.id.ImageView);

        now = rootData.child("current");
        now.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sekarang=dataSnapshot.getValue().toString();

                info = rootData.child("Login Details").child("user"+sekarang).child("carPlate");
                info.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        text = dataSnapshot.getValue().toString();
                        if (text != null) {
                            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

                            try {
                                BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500);
                                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                                ImageView.setImageBitmap(bitmap);
                            } catch (WriterException e) {
                                e.printStackTrace();
                            }
                        }
                        Toast.makeText(QRCode.this,"QR Code Generated!" , Toast.LENGTH_LONG).show();

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
        butt = findViewById(R.id.btn);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change = new Intent(QRCode.this, mainMenu.class);
                startActivity(change);
                finish();
            }
        });
    }
}






