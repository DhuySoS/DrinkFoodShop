package com.example.drinkfoodshop.setting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.home.trangChu;
import com.example.drinkfoodshop.loginAndRegister.RegisterActivity;
import com.example.drinkfoodshop.loginAndRegister.intro;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class settingActivity extends AppCompatActivity {
    private TextView back;
    private Button btnLogout, btnDoiMk, btnXoaTk;
    private FirebaseAuth mAuth;
    private FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
// function
        anhXa();
        onclick();

    }

    private void anhXa() {
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        back = findViewById(R.id.caiDatTxt);
        btnLogout = findViewById(R.id.btnDangXuat);
        btnXoaTk = findViewById(R.id.btnXoaTk);
        btnDoiMk = findViewById(R.id.btnDoiMk);


    }

    private void onclick() {
    //trở về
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settingActivity.this, trangChu.class);
                startActivity(intent);
                finish();
            }
        });
    // Nút Đăng xuất
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(settingActivity.this, intro.class);
                startActivity(intent);
                finish();
            }
        });
    // Nút đổi mật khẩu
        btnDoiMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress = user.getEmail();

                mAuth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(settingActivity.this,"Đã gửi email đổi mật khẩu",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    // Nút xóa tài khoản
        btnXoaTk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(settingActivity.this,"Xóa tài khoản thành công",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(settingActivity.this,intro.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });

    }

}