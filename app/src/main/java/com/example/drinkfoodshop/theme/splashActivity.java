package com.example.drinkfoodshop.theme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.home.trangChu;
import com.example.drinkfoodshop.loginAndRegister.intro;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        },2000);
    }

    private void nextActivity() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            //chua login
            Intent intent = new Intent(this, intro.class);
            startActivity(intent);
        } else {
            //da login
            Intent intent = new Intent(this, trangChu.class);
            startActivity(intent);
        }
        finish();
    }
}