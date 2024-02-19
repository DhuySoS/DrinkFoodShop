package com.example.drinkfoodshop.Profile1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.cart.cart;
import com.example.drinkfoodshop.databinding.ActivityProfile1Binding;
import com.example.drinkfoodshop.foodList.listFoodsActivity;
import com.example.drinkfoodshop.help.CustomerSupportActivity;
import com.example.drinkfoodshop.home.trangChu;
import com.example.drinkfoodshop.saveHistory.YourHistoryActivity;
import com.example.drinkfoodshop.setting.settingActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile1 extends AppCompatActivity {
    private ActivityProfile1Binding binding;
    private TextView nameTxt, emailTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfile1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        setVariable();
        showUserInfor();
    }

    private void showUserInfor() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }

        String name = user.getDisplayName();
        String email = user.getEmail();

        if(name == null ){
            nameTxt.setVisibility(View.GONE);
        }else {
            nameTxt.setVisibility(View.VISIBLE);
            nameTxt.setText(name);
        }
        emailTxt.setText(email);
    }

    private void initUi() {
        nameTxt = findViewById(R.id.tv_Name);
        emailTxt = findViewById(R.id.tv_email);
    }

    private void setVariable() {
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile1.this, trangChu.class);
                startActivity(intent);
                finish();
            }
        });
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile1.this, settingActivity.class);
                startActivity(intent);
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile1.this, listFoodsActivity.class);
                startActivity(intent);
            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile1.this, cart.class);
                startActivity(intent);
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi nút "Lịch Sử Đơn Hàng" được bấm
                Intent intent = new Intent(Profile1.this, YourHistoryActivity.class); // Thay YourHistoryActivity bằng tên Activity của bạn
                startActivity(intent);
            }
        });

        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile1.this, CustomerSupportActivity.class);
                startActivity(intent);
            }
        });
    }
}
