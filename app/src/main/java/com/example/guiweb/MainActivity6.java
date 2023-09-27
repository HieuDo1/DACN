package com.example.guiweb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class MainActivity6 extends AppCompatActivity {

    EditText edt1, edt2, edt3, edt4;
    Button bt;
    private DatabaseReference databaseReference;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

    edt1 = findViewById(R.id.edt1);
    edt2 = findViewById(R.id.edt2);
    edt3 = findViewById(R.id.edt3);
    edt4 = findViewById(R.id.edt4);
    bt = findViewById(R.id.bt);
    mDatabase = FirebaseDatabase.getInstance().getReference();
    databaseReference = FirebaseDatabase.getInstance().getReference();


    bt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String a = edt1.getText().toString();
            String b = edt2.getText().toString();
            String c = edt3.getText().toString();
            String d = edt4.getText().toString();
            mDatabase.child("Sp1").setValue(a);
            mDatabase.child("Sp2").setValue(b);
            mDatabase.child("Sp3").setValue(c);
            mDatabase.child("Sp4").setValue(d);

            onBackPressed();
        }
    });


    }
}