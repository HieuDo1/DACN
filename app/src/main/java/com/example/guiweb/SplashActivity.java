package com.example.guiweb;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView  anh2;
    private TextView textView;
    private ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textView= findViewById(R.id.textView);
        anh2 = findViewById(R.id.anh2);



        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        textView.startAnimation(fadeInAnimation);



        Animation pulseAnimation = AnimationUtils.loadAnimation(this, R.anim.pulse_effect);
        anh2.startAnimation(pulseAnimation);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(pulseAnimation);
        animationSet.addAnimation(fadeInAnimation);




        // Sau khi animation hoàn thành, chuyển đến màn hình chính hoặc màn hình tiếp theo
        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Khởi động animation
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Chuyển đến màn hình chính hoặc màn hình tiếp theo
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Lặp lại animation nếu cần
            }
        });


    }
}