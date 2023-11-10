package com.example.guiweb;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity4 extends AppCompatActivity {

    private Button button1, button2, button3, button4;

    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        databaseRef = FirebaseDatabase.getInstance().getReference();

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPasswordDialog1();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPasswordDialog2();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                showPasswordDialog3();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPasswordDialog4();
            }
        });

        }
        // kho1
    private void showPasswordDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nhập mật khẩu của bạn");

        final EditText passwordEditText = new EditText(this);
        builder.setView(passwordEditText);

        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String password = passwordEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(password)) {
                    checkPassword1(password);
                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void checkPassword1(final String password) {
        databaseRef.child("matkhaukho1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String firebasePassword = dataSnapshot.getValue(String.class);
                if (firebasePassword != null && firebasePassword.equals(password))
                {
                    sendCommandToFirebase("kho1");
                } else
                {

                    Toast.makeText(MainActivity4.this, "Sai mật khẩu ", Toast.LENGTH_SHORT).show();
                    // Mật khẩu không đúng
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi
            }
        });
    }

    // kho2
    private void showPasswordDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nhập mật khẩu của bạn");

        final EditText passwordEditText = new EditText(this);
        builder.setView(passwordEditText);

        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String password = passwordEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(password)) {
                    checkPassword2(password);
                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void checkPassword2(final String password) {
        databaseRef.child("matkhaukho2").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String firebasePassword = dataSnapshot.getValue(String.class);
                if (firebasePassword != null && firebasePassword.equals(password)) {
                    sendCommandToFirebase("kho2");
                } else {

                    Toast.makeText(MainActivity4.this, "Sai mật khẩu ", Toast.LENGTH_SHORT).show();
                    // Mật khẩu không đúng
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi
            }
        });
    }

    // kho3
    private void showPasswordDialog3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nhập mật khẩu của bạn");

        final EditText passwordEditText = new EditText(this);
        builder.setView(passwordEditText);

        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String password = passwordEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(password)) {
                    checkPassword3(password);
                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void checkPassword3(final String password) {
        databaseRef.child("matkhaukho3").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String firebasePassword = dataSnapshot.getValue(String.class);
                if (firebasePassword != null && firebasePassword.equals(password)) {
                    sendCommandToFirebase("kho3");
                } else {

                    Toast.makeText(MainActivity4.this, "Sai mật khẩu ", Toast.LENGTH_SHORT).show();
                    // Mật khẩu không đúng
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi
            }
        });
    }

    // kho4
    private void showPasswordDialog4() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nhập mật khẩu của bạn");

        final EditText passwordEditText = new EditText(this);
        builder.setView(passwordEditText);

        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String password = passwordEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(password)) {
                    checkPassword4(password);
                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void checkPassword4(final String password) {
        databaseRef.child("matkhaukho4").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String firebasePassword = dataSnapshot.getValue(String.class);
                if (firebasePassword != null && firebasePassword.equals(password)) {
                    sendCommandToFirebase("kho4");
                } else {

                    Toast.makeText(MainActivity4.this, "Sai mật khẩu ", Toast.LENGTH_SHORT).show();
                    // Mật khẩu không đúng
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi
            }
        });
    }

    private void sendCommandToFirebase(String command) {
        databaseRef.child("LenhLay").setValue(command)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Gửi thành công
                        } else {
                            // Gửi thất bại
                        }
                    }
                });
    }

}

