package com.example.drinkfoodshop.loginAndRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.home.trangChu;
import com.example.drinkfoodshop.theme.forgotPassActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Button btnSignIn;
    private EditText strEmail,strpass;
    private ProgressDialog progressDialog;
    private TextView forgotPassTv;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignIn = findViewById(R.id.btnDangNhap);
        strEmail = findViewById(R.id.editEmail);
        strpass = findViewById(R.id.editPass);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        forgotPassTv = findViewById(R.id.forgotPassTxt);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = strEmail.getText().toString();
                password = strpass.getText().toString();
                progressDialog.show();
                if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"Không được để trống email hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                }else {
                    mAuth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()){
                                        // dang nhap thanh cong
                                        Intent intent = new Intent(LoginActivity.this, trangChu.class);
                                        startActivity(intent);
                                        finishAffinity();
                                    }else {
                                        Toast.makeText(LoginActivity.this,"Tài khoản hoặc mật khẩu không chính xác ",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        forgotPassTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, forgotPassActivity.class);
                startActivity(intent);
            }
        });
    }

    public void dangKy(View view) {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }
}