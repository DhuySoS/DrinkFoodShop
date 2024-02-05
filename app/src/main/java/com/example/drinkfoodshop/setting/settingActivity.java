package com.example.drinkfoodshop.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.loginAndRegister.intro;
import com.google.firebase.auth.FirebaseAuth;

public class settingActivity extends AppCompatActivity {
    private Button btnLogout;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mAuth = FirebaseAuth.getInstance();
        btnLogout = findViewById(R.id.btnLogout);
// function
        onclick();
    }

    private void onclick() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(settingActivity.this, intro.class);
                startActivity(intent);
                finish();
            }
        });


    }

}