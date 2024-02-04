package com.example.drinkfoodshop.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.home.trangChu;
import com.example.drinkfoodshop.loginAndRegister.intro;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvLogOut;
    private FirebaseAuth mAuth;
    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        tvLogOut = findViewById(R.id.textView105);
        Back = findViewById(R.id.imgBack);
// function
        onclick();
    }

    private void onclick() {
        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileActivity.this, intro.class);
                startActivity(intent);
                finish();
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, trangChu.class);
                startActivity(intent);
                finish();
            }
        });
    }

}