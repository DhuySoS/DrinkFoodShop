package com.example.drinkfoodshop.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.adapter.catagoryAdapter;
import com.example.drinkfoodshop.domain.categoryDomain;

import java.util.ArrayList;

public class trangChu extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewCategory();
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<categoryDomain> category = new ArrayList<>();
        category.add(new categoryDomain("Pizza","cat_2"));
        category.add(new categoryDomain("Burger","cat_4"));
        category.add(new categoryDomain("Chicken","cat_2"));
        category.add(new categoryDomain("Sushi","cat_4"));
        category.add(new categoryDomain("Meat","cat_2"));
        category.add(new categoryDomain("Hotdog","cat_4"));
        category.add(new categoryDomain("Drink","cat_2"));
        category.add(new categoryDomain("More","cat_2"));
        
        adapter = new catagoryAdapter(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}
