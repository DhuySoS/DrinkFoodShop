package com.example.drinkfoodshop.setting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.home.trangChu;
import com.example.drinkfoodshop.loginAndRegister.RegisterActivity;
import com.example.drinkfoodshop.loginAndRegister.intro;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class settingActivity extends AppCompatActivity {
    private TextView back, emailTv, doiTenTv;
    EditText editName;
    private Button btnLogout, btnDoiMk, btnXoaTk;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        dialog = new ProgressDialog(settingActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading.....");
// function
        anhXa();
        onclick();
        setUserInfor();
    }

    private void setUserInfor() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        emailTv.setText(user.getEmail());
        editName.setText(user.getDisplayName());
    }

    private void anhXa() {
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        back = findViewById(R.id.caiDatTxt);

        emailTv = findViewById(R.id.EmailTv);
        doiTenTv = findViewById(R.id.tvDoiTen);
        editName = findViewById(R.id.editName);

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
                changePassworDialog();
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
    // doi ten
        doiTenTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user == null){
                    return;
                }
                String strName = editName.getText().toString();
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(strName)
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(settingActivity.this,"Đổi tên thành công",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void changePassworDialog() {
        Dialog dialog = new Dialog(settingActivity.this);
        dialog.setContentView(R.layout.activity_change_password);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText oldPass = dialog.findViewById(R.id.oldPass);
        EditText newPass = dialog.findViewById(R.id.newPass);
        EditText reEnterPass = dialog.findViewById(R.id.re_enter_pass);
        Button updatePass = dialog.findViewById(R.id.btnChangePass);

        updatePass.setOnClickListener(v ->{
            String oldPassStr = oldPass.getText().toString();
            String newPassStr = newPass.getText().toString();
            String reEnterPassStr = reEnterPass.getText().toString();
            if(TextUtils.isEmpty(oldPassStr)){
                oldPass.setError("Nhập mật khẩu cũ");
            } else if (newPassStr.isEmpty()) {
                newPass.setError("Không được để trống");
            } else if (reEnterPassStr.isEmpty()) {
                reEnterPass.setError("Không được để trống");
            } else if (newPassStr.compareTo(reEnterPassStr) !=0) {
                Toast.makeText(settingActivity.this,"Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            }else {
                updatePassword(oldPassStr,newPassStr);
            }
        });
        dialog.show();
    }

    private void updatePassword(String oldPassStr, String newPassStr) {
        dialog.show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(),oldPassStr);

        user.reauthenticate(authCredential).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                user.updatePassword(newPassStr).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        dialog.dismiss();
                        Toast.makeText(settingActivity.this,"Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(settingActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}