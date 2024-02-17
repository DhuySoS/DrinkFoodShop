package com.example.drinkfoodshop.Profile1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.cart.cart;
import com.example.drinkfoodshop.foodList.listFoodsActivity;
import com.example.drinkfoodshop.help.CustomerSupportActivity;
import com.example.drinkfoodshop.home.trangChu;

import android.content.Intent;
import android.view.View;

public class Profile1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile1.this, listFoodsActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile1.this, cart.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile1.this, CustomerSupportActivity.class);
                startActivity(intent);
            }
        });


    }
}
