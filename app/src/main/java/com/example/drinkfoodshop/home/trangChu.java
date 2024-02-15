package com.example.drinkfoodshop.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinkfoodshop.Profile1.Profile1;
import com.example.drinkfoodshop.Profile1.ProfileActivity;
import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.adapter.bestFoodAdapter;
import com.example.drinkfoodshop.adapter.catagoryAdapter;
import com.example.drinkfoodshop.cart.cart;
import com.example.drinkfoodshop.databinding.ActivityHomeBinding;
import com.example.drinkfoodshop.domain.categoryDomain;
import com.example.drinkfoodshop.domain.food;
import com.example.drinkfoodshop.foodList.listFoodsActivity;
import com.example.drinkfoodshop.help.CustomerSupportActivity;
import com.example.drinkfoodshop.setting.settingActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class trangChu extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    private LinearLayout lProfile, lCart, lHelp, lSetting;
    private FloatingActionButton btnCart;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        lProfile = findViewById(R.id.linearProfile);
//        lCart=findViewById(R.id.linearCart);
        btnCart=findViewById(R.id.cartHome);
        lHelp = findViewById(R.id.linearHelp);
        lSetting = findViewById(R.id.linearSetting);

        onclick();
        recyclerViewCategory();
        initBestFood();
        setVariable();
    }

    private void setVariable() {
        binding.searchTxt.setOnClickListener(v -> {
            String text = binding.searchTxt.getText().toString();
            if(!text.isEmpty()){
                Intent intent = new Intent(trangChu.this, listFoodsActivity.class);
                intent.putExtra("text", text);
                intent.putExtra("isSearch",true);
                startActivity((intent));
            }
        });
    }


    private void onclick() {
// click vào Cá nhân
        lProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trangChu.this, Profile1.class);
                startActivity(intent);
                finish();
            }
        });
// click vào giỏ hàng
    btnCart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(trangChu.this, cart.class));
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
                Intent intent = new Intent(trangChu.this, settingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //hien thi cyclerView Category
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<categoryDomain> category = new ArrayList<>();
        category.add(new categoryDomain("Pizza", "cat_1",0));
        category.add(new categoryDomain("Burger", "cat_2",1));
        category.add(new categoryDomain("Chicken", "cat_3",2));
        category.add(new categoryDomain("Sushi", "cat_4",3));
        category.add(new categoryDomain("Meat", "cat_5",4));
        category.add(new categoryDomain("Hotdog", "cat_6",5));
        category.add(new categoryDomain("Drink", "cat_7",6));
        category.add(new categoryDomain("More", "cat_8",7));

        adapter = new catagoryAdapter(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    // best food
    private void initBestFood() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://drink-food-shop-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Foods");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<food> list = new ArrayList<>();
        Query query = myRef.orderByChild("BestFood").equalTo(false);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(food.class));
                    }
                    if (list.size() > 0) {
                        binding.recyclerViewBestFood.setLayoutManager(new LinearLayoutManager(trangChu.this, LinearLayoutManager.HORIZONTAL, false));
//                        binding.recyclerViewBestFood.setLayoutManager(new GridLayoutManager(trangChu.this, 2));
                        RecyclerView.Adapter adapter1 = new bestFoodAdapter(list);
                        binding.recyclerViewBestFood.setAdapter(adapter1);
                    }
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
