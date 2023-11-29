package com.example.guiweb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button bt3, bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);

    bt3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i2 = new Intent(MainActivity.this,MainActivity2.class);
            startActivity(i2);
        }
    });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(MainActivity.this,MainActivity4.class);
                startActivity(i3);
            }
        });

    }

}