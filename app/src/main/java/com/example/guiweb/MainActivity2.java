package com.example.guiweb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity2 extends AppCompatActivity {

    Button bt, bt3;
    TextView tv, tv1, tv2, tv3, tv4;
    private DatabaseReference databaseReference;
    private DatabaseReference mDatabase;
    private DatabaseReference Sp1, Sp2, Sp3, Sp4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bt = findViewById(R.id.bt);
        bt3 = findViewById(R.id.bt3);
        tv = findViewById(R.id.tv);
        tv1 = findViewById(R.id.tv1);
        tv2= findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Sp1 = FirebaseDatabase.getInstance().getReference("Sp1");
        Sp2 = FirebaseDatabase.getInstance().getReference("Sp2");
        Sp3 = FirebaseDatabase.getInstance().getReference("Sp3");
        Sp4 = FirebaseDatabase.getInstance().getReference("Sp4");


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity2.this);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setPrompt("Quet ma QR");
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.initiateScan();
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(i1);
            }
        });
        Sp1.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    Object obj = task.getResult().getValue();
                    tv1.setText("Sp1 trong kho còn: "+obj.toString());

                }
            }
        });
        Sp2.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    Object obj = task.getResult().getValue();
                    tv2.setText("Sp2 trong kho còn:"+obj.toString());

                }
            }
        });
        Sp3.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    Object obj = task.getResult().getValue();
                    tv3.setText("Sp3 trong kho còn:" + obj.toString());

                }
            }
        });
        Sp4.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    Object obj = task.getResult().getValue();
                    tv4.setText("Sp4 trong kho còn:"+obj.toString());

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(intentResult != null){
            String contents = intentResult.getContents();
            if(contents != null){
                tv.setText(intentResult.getContents());
                mDatabase.child("Laysp").setValue(intentResult.getContents());
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
