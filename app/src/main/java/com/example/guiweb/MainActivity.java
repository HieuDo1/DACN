package com.example.guiweb;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private ToggleButton lightToggleButton;
    private ImageButton rotateImageButton;
    private Animation rotateAnimation;
    private boolean isRotating = false;

    private TextView gasTextView;
    private TextView temperatureTextView;
    private DatabaseReference temperatureRef;
    private DatabaseReference gasRef;
    private DatabaseReference lightRef;
    private DatabaseReference quatRef;
    private ImageView maysayImageView;
    private ImageView bomImageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightToggleButton = findViewById(R.id.lightToggleButton);
        rotateImageButton = findViewById(R.id.rotateImageButton);
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);

        gasTextView = findViewById(R.id.gasTextView);
        temperatureTextView = findViewById(R.id.temperatureTextView);
        maysayImageView = findViewById(R.id.maysayImageView);
        bomImageView = findViewById(R.id.bomImageView);

        temperatureRef = FirebaseDatabase.getInstance().getReference().child("NhietDo");
        gasRef = FirebaseDatabase.getInstance().getReference().child("KhiGas");
        lightRef = FirebaseDatabase.getInstance().getReference().child("Den");
        quatRef = FirebaseDatabase.getInstance().getReference().child("Quat");


        temperatureRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String temperature = dataSnapshot.getValue(String.class);
                    temperatureTextView.setText("Nhiệt độ: " + temperature + "°C");

                    // Kiểm tra điều kiện nhiệt độ để gửi thông báo
                    if (Integer.parseInt(temperature) > 30) {
                        // Ghi dữ liệu vào Realtime Database
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("MayLanh");
                        databaseReference.setValue("Bat");
                        maysayImageView.setVisibility(View.VISIBLE);
                    } else {
                        // Ghi dữ liệu vào Realtime Database
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("MayLanh");
                        databaseReference.setValue("Tat");
                        maysayImageView.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi khi đọc dữ liệu từ Firebase
            }
        });

        gasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String gas = dataSnapshot.getValue(String.class);
                    gasTextView.setText("Khí gas: " + gas);

                    // Kiểm tra điều kiện khí gas để gửi thông báo
                    if (Integer.parseInt(gas) > 2000) {
                        // Ghi dữ liệu vào Realtime Database
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("MayBomChay");
                        databaseReference.setValue("Bat");
                        bomImageView.setVisibility(View.VISIBLE);
                    } else {
                        // Ghi dữ liệu vào Realtime Database
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("MayBomChay");
                        databaseReference.setValue("Tat");
                        bomImageView.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi khi đọc dữ liệu từ Firebase
            }
        });

        //Kiểm tra trạng thái đèn trên firebase
        lightRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String lightStatus = dataSnapshot.getValue(String.class);

                    // Kiểm tra trạng thái và cập nhật lightToggleButton
                    if (lightStatus != null && lightStatus.equals("Bat")) {
                        lightToggleButton.setChecked(true);
                        lightToggleButton.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                    } else {
                        lightToggleButton.setChecked(false);
                        lightToggleButton.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi khi đọc dữ liệu từ Firebase
            }
        });

        //Kiểm tra trạng thái quạt trên firebase
        quatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String quatStatus = dataSnapshot.getValue(String.class);

                    // Kiểm tra trạng thái và cập nhật rotateImageButton và rotateAnimation
                    if (quatStatus != null && quatStatus.equals("Bat")) {
                        if (!isRotating) {
                            rotateImageButton.startAnimation(rotateAnimation);
                            isRotating = true;
                        }
                    } else {
                        if (isRotating) {
                            rotateImageButton.clearAnimation();
                            isRotating = false;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi khi đọc dữ liệu từ Firebase
            }
        });



    }

    public void toggleLight(View view) {
        boolean isChecked = lightToggleButton.isChecked();
        int thumbTint = isChecked ? R.color.green : R.color.gray;
        lightToggleButton.setButtonTintList(getResources().getColorStateList(thumbTint));

        // Ghi dữ liệu vào Realtime Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Den");
        if (isChecked) {
            databaseReference.setValue("Bat");
        } else {
            databaseReference.setValue("Tat");
        }
    }


    public void rotateImage(View view) {
        if (isRotating) {
            rotateImageButton.clearAnimation();
            isRotating = false;

            // Ghi dữ liệu vào Realtime Database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Quat");
            databaseReference.setValue("Tat");
        } else {
            rotateImageButton.startAnimation(rotateAnimation);
            isRotating = true;

            // Ghi dữ liệu vào Realtime Database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Quat");
            databaseReference.setValue("Bat");
        }
    }
}