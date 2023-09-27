package com.example.guiweb;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    private Button button;
    Button button2, bt3;

    private TextView textView, tv1, tv2, tv3, tv4;
    private DatabaseReference databaseReference;
    private DatabaseReference mDatabase;
    private DatabaseReference Sp1, Sp2, Sp3, Sp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        bt3 = findViewById(R.id.bt3);
        tv1 = findViewById(R.id.tv1);
        tv2= findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Laysp").setValue("");
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Sp1 = FirebaseDatabase.getInstance().getReference("Sp1");
        Sp2 = FirebaseDatabase.getInstance().getReference("Sp2");
        Sp3 = FirebaseDatabase.getInstance().getReference("Sp3");
        Sp4 = FirebaseDatabase.getInstance().getReference("Sp4");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo một yêu cầu nhận dạng ngôn ngữ mới.
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hãy nói gì đó...");

                // Khởi chạy yêu cầu nhận dạng ngôn ngữ.
                startActivityForResult(intent, 100);
            }
        });


        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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
                    tv2.setText("Sp2 trong kho còn: "+obj.toString());

                }
            }
        });
        Sp3.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    Object obj = task.getResult().getValue();
                    tv3.setText("Sp3 trong kho còn: "+obj.toString());

                }
            }
        });
        Sp4.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    Object obj = task.getResult().getValue();
                    tv4.setText("Sp4 trong kho còn: "+obj.toString());

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Kiểm tra xem yêu cầu nhận dạng ngôn ngữ có thành công hay không.
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            // Lấy danh sách các phản hồi từ yêu cầu nhận dạng ngôn ngữ.
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            // Hiển thị phản hồi đầu tiên trên màn hình.
            textView.setText(results.get(0));
            mDatabase.child("Lenh").setValue(results.get(0));
        }
    }
}