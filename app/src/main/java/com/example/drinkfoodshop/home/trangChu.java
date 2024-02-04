package com.example.drinkfoodshop.home;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.adapter.catagoryAdapter;
import com.example.drinkfoodshop.domain.categoryDomain;
import com.example.drinkfoodshop.help.CustomerSupportActivity;
import com.example.drinkfoodshop.loginAndRegister.intro;
import com.example.drinkfoodshop.profile.ProfileActivity;

import java.util.ArrayList;

public class trangChu extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    private LinearLayout lProfile, lCart, lHelp, lSetting ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        lProfile =  findViewById(R.id.linearProfile);
        lCart =  findViewById(R.id.linearCart);
        lHelp =  findViewById(R.id.linearHelp);
        lSetting =  findViewById(R.id.linearSetting);
        recyclerViewCategory();
        onclick();
    }

    private void onclick() {
// click vào Cá nhân
        lProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trangChu.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
// click vào giỏ hàng
        lCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trangChu.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
// click vào Trợ giúp
        lHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trangChu.this, CustomerSupportActivity.class);
                startActivity(intent);
                finish();
            }
        });
// click vào cài đặt
        lSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trangChu.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<categoryDomain> category = new ArrayList<>();
        category.add(new categoryDomain("Pizza","cat_1"));
        category.add(new categoryDomain("Burger","cat_2"));
        category.add(new categoryDomain("Chicken","cat_3"));
        category.add(new categoryDomain("Sushi","cat_4"));
        category.add(new categoryDomain("Meat","cat_5"));
        category.add(new categoryDomain("Hotdog","cat_6"));
        category.add(new categoryDomain("Drink","cat_7"));
        category.add(new categoryDomain("More","cat_8"));
        
        adapter = new catagoryAdapter(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}
