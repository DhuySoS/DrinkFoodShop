package com.example.drinkfoodshop.loginAndRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.home.trangChu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private Button btnRegister;
    private EditText editEmail, editPass, confirmPass;
    private TextView errorText;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        btnRegister = findViewById(R.id.btnDangKy);
        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPass);
        confirmPass = findViewById(R.id.confirmPass);
        errorText =findViewById(R.id.errorTv);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, confirmPassword;
                email = editEmail.getText().toString();
                password = editPass.getText().toString();
                confirmPassword = confirmPass.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this,"Không được để trống",Toast.LENGTH_SHORT).show();
                } else if (password.equals(confirmPassword)) {
                    //tien hanh dang ky tai khoan
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                //khi dang ky thanh cong
                                Toast.makeText(RegisterActivity.this,"Đăng ký tài khoản thành công",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, trangChu.class);
                                startActivity(intent);
                                finishAffinity();
                            }else{
                            //khi dang ky that bai
                                Toast.makeText(RegisterActivity.this, "Đăng ký tài khoản thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    //mat khau khong khop dua ra thong bao
                    errorText.setVisibility(View.VISIBLE);
                    errorText.setText("Mật khẩu không khớp");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            errorText.setVisibility(View.GONE);
                        }
                    }, 3000);
                }
            }
        });
    }

    public void dangNhap(View view) {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }
}