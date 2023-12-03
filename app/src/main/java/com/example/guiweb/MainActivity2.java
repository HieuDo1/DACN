package com.example.guiweb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity2 extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 200;

    private Button bt;
    TextView tv, tvmk;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bt = findViewById(R.id.bt);

        tv = findViewById(R.id.tv);
        tvmk = findViewById(R.id.tvmk);

        sharedPref = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        String savedPassword = sharedPref.getString("Mat khau", "");
        String savedQrValue = sharedPref.getString("Kho", "");
        if (!savedPassword.isEmpty()) {
            tv.setText("Đồ của bạn đã được lưu vào kho: "+savedQrValue);
        }
        if (!savedQrValue.isEmpty()) {
            tvmk.setText("Mật khẩu của bạn là: "+savedPassword);
        }
        bt .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra quyền truy cập máy ảnh
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity2.this);
                intentIntegrator.setOrientationLocked(true);

                startQRScan();
            }
        });

    }

    private void startQRScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Quét mã QR");
        integrator.setCameraId(0);  // Sử dụng camera sau
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Lấy kết quả từ IntentIntegrator
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            if (result.getContents() != null) {
                String qrValue = result.getContents();
                tv.setText("Đồ của bạn đã được lưu vào kho "+qrValue);
                // Lưu giá trị mã QR vào Firebase
                saveQRValueToFirebase(qrValue);

                // Tạo mật khẩu ngẫu nhiên
                String randomPassword = generateRandomPassword();

                tvmk.setText("Mật khẩu của bạn là: "+randomPassword);
                // Lưu mật khẩu vào Firebase tương ứng với giá trị mã QR
                if (qrValue.equals("kho 1")) {
                    savePasswordToFirebase("matkhaukho1", randomPassword);
                }
                else if (qrValue.equals("kho 2")) {
                    savePasswordToFirebase("matkhaukho2", randomPassword);
                }
                else if (qrValue.equals("kho 3")) {
                    savePasswordToFirebase("matkhaukho3", randomPassword);
                }
                else if (qrValue.equals("kho 4")) {
                    savePasswordToFirebase("matkhaukho4", randomPassword);
                }
                // Hiển thị thông báo thành công
                Toast.makeText(this, "Mã QR đã được quét và lưu vào Firebase.", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("Mat khau", randomPassword);
                editor.putString("Kho", qrValue);
                editor.apply();
            }
        }
    }

    private void saveQRValueToFirebase(String qrValue) {
        // Thay đổi 'your-firebase-database' thành tên thực tế của Firebase Database của bạn
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("LenhGui");
        databaseReference.setValue(qrValue);
    }

    private void savePasswordToFirebase(String key, String password) {
        // Thay đổi 'your-firebase-database' thành tên thực tế của Firebase Database của bạn
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(key);
        databaseReference.setValue(password);
    }

    private String generateRandomPassword() {
        // Tạo mật khẩu ngẫu nhiên gồm 4 số
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int randomDigit = (int) (Math.random() * 10);
            password.append(randomDigit);
        }
        return password.toString();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Tiếp tục quét mã QR sau khi đã được cấp quyền truy cập máy ảnh
                startQRScan();
            } else {
                Toast.makeText(this, "Ứng dụng không được cấp quyền truy cập máy ảnh.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}