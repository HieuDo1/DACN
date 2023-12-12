package com.example.guiweb;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button bt3, bt4;
    ImageView goihang, tay2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        goihang = findViewById(R.id.goihang);
        tay2 = findViewById(R.id.tay2);


        Animation flyin = AnimationUtils.loadAnimation(this, R.anim.fly_in_from_left);
        flyin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Được gọi khi hiệu ứng bắt đầu
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                // Được gọi khi hiệu ứng kết thúc
                goihang.startAnimation(flyin); // Lặp lại hiệu ứng
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                // Được gọi khi hiệu ứng được lặp lại (không được sử dụng trong trường hợp này)
            }
        });
        goihang.startAnimation(flyin); // Bắt đầu hiệu ứng


        Animation flyin2 = AnimationUtils.loadAnimation(this, R.anim.fly_in_from_right);
        flyin2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Được gọi khi hiệu ứng bắt đầu
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                // Được gọi khi hiệu ứng kết thúc
                tay2.startAnimation(flyin2); // Lặp lại hiệu ứng
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                // Được gọi khi hiệu ứng được lặp lại (không được sử dụng trong trường hợp này)
            }
        });
        tay2.startAnimation(flyin2); // Bắt đầu hiệu ứng


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