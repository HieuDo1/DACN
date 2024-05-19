package com.example.guiweb;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private ToggleButton lightToggleButton1;
    private ToggleButton lightToggleButton2;
    private ImageButton rotateImageButton;
    private Animation rotateAnimation;
    private boolean isRotating = false;

    private TextView gasTextView;
    private TextView temperatureTextView;
    private DatabaseReference temperatureRef;
    private DatabaseReference gasRef;
    private DatabaseReference lightRef1;
    private DatabaseReference lightRef2;
    private DatabaseReference quatRef;

    private ImageView maysayImageView;
    private ImageView bomImageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightToggleButton1 = findViewById(R.id.lightToggleButton1);
        lightToggleButton2 = findViewById(R.id.lightToggleButton2);
        rotateImageButton = findViewById(R.id.rotateImageButton);
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);

        gasTextView = findViewById(R.id.gasTextView);
        temperatureTextView = findViewById(R.id.temperatureTextView);
        maysayImageView = findViewById(R.id.maysayImageView);
        bomImageView = findViewById(R.id.bomImageView);

        temperatureRef = FirebaseDatabase.getInstance().getReference().child("NhietDo");
        gasRef = FirebaseDatabase.getInstance().getReference().child("KhiGas");
        lightRef1 = FirebaseDatabase.getInstance().getReference().child("Den1");
        lightRef2 = FirebaseDatabase.getInstance().getReference().child("Den2");
        quatRef = FirebaseDatabase.getInstance().getReference().child("Quat");



        temperatureRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer temperature = dataSnapshot.getValue(Integer.class);
                    temperatureTextView.setText("Nhiệt độ: " + temperature + "°C");

                    // Kiểm tra điều kiện nhiệt độ để gửi thông báo
                    int temperatureInt = Integer.parseInt(String.valueOf(temperature));
                    if (temperatureInt > 30) {
                        // Ghi dữ liệu vào Realtime Database
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("MayLanh");
                        databaseReference.setValue("Bat");

                        if (maysayImageView != null) {
                            maysayImageView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        // Ghi dữ liệu vào Realtime Database
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("MayLanh");
                        databaseReference.setValue("Tat");

                        if (maysayImageView != null) {
                            maysayImageView.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error condition if necessary
            }
        });

        gasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String gas = dataSnapshot.getValue(String.class);
                    gasTextView.setText("Khí gas: " + gas);

                    // Kiểm tra điều kiện khí gas để gửi thông báo
                    int gasInt = Integer.parseInt(gas);
                    if (gasInt > 2000) {
                        // Ghi dữ liệu vào Realtime Database
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("MayBomChay");
                        databaseReference.setValue("Bat");

                        if (bomImageView != null) {
                            bomImageView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        // Ghi dữ liệu vào Realtime Database
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("MayBomChay");
                        databaseReference.setValue("Tat");

                        if (bomImageView != null) {
                            bomImageView.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra trong quá trình đọc dữ liệu
            }
        });

        //Kiểm tra trạng thái đèn trên firebase
        lightRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String lightStatus1 = dataSnapshot.getValue(String.class);

                    // Kiểm tra trạng thái và cập nhật lightToggleButton
                    if (lightStatus1 != null && lightStatus1.equals("Bat")) {
                        lightToggleButton1.setChecked(true);
                        lightToggleButton1.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                    } else {
                        lightToggleButton1.setChecked(false);
                        lightToggleButton1.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi khi đọc dữ liệu từ Firebase
            }
        });



        lightRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String lightStatus2 = dataSnapshot.getValue(String.class);

                    // Kiểm tra trạng thái và cập nhật lightToggleButton
                    if (lightStatus2 != null && lightStatus2.equals("Bat")) {
                        lightToggleButton2.setChecked(true);
                        lightToggleButton2.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                    } else {
                        lightToggleButton2.setChecked(false);
                        lightToggleButton2.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
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

    private void updateLightStatus(@NonNull String lightName, boolean isChecked) {
        int thumbTint = isChecked ? R.color.green : R.color.gray;
        int[][] states = {{android.R.attr.state_checked}, {}};
        int[] colors = {ContextCompat.getColor(this, thumbTint), ContextCompat.getColor(this, thumbTint)};
        ColorStateList colorStateList = new ColorStateList(states, colors);

        lightToggleButton1.setButtonTintList(colorStateList); // Đối với lightToggleButton1
        lightToggleButton2.setButtonTintList(colorStateList); // Đối với lightToggleButton2

        // Ghi dữ liệu vào Realtime Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        if (lightName.equals("Den1")) {
            databaseReference.child("Den1").setValue(isChecked ? "Bat" : "Tat");
        } else if (lightName.equals("Den2")) {
            databaseReference.child("Den2").setValue(isChecked ? "Bat" : "Tat");
        }
    }

    public void toggleLight1(View view) {
        boolean isChecked = lightToggleButton1.isChecked();
        updateLightStatus("Den1", isChecked);
    }

    public void toggleLight2(View view) {
        boolean isChecked = lightToggleButton2.isChecked();
        updateLightStatus("Den2", isChecked);
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