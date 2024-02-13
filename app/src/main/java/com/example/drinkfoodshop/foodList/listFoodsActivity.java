package com.example.drinkfoodshop.foodList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.adapter.foodListAdapter;
import com.example.drinkfoodshop.databinding.ActivityListFoodsBinding;
import com.example.drinkfoodshop.domain.food;
import com.example.drinkfoodshop.home.trangChu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listFoodsActivity extends AppCompatActivity {
    ActivityListFoodsBinding binding;
    private RecyclerView.Adapter adapterListFood;
    private int Id;
    private String Name;
    private String searchText;
    private boolean isSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityListFoodsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        getIntentExtra();

        initList();
    }

    private void initList() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://drink-food-shop-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Foods");
        binding.progressBarListFoods.setVisibility(View.VISIBLE);
        ArrayList<food> list = new ArrayList<>();

        Query query;
        if(isSearch){
            query=myRef.orderByChild("Title").startAt(searchText).endAt(searchText+'\uf8ff');
        }else {
            query = myRef.orderByChild("CategoryId").equalTo(Id);
        }
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(food.class));
                    }
                    if(list.size()>0){
                        binding.foodListView.setLayoutManager(new GridLayoutManager(listFoodsActivity.this,2));
                        adapterListFood=new foodListAdapter(list);
                        binding.foodListView.setAdapter(adapterListFood);
                    }
                    binding.progressBarListFoods.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIntentExtra() {
        Id = getIntent().getIntExtra("CategoryId",0);
        Name = getIntent().getStringExtra("CategoryName");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch",false);

        binding.titletxt.setText(Name);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listFoodsActivity.this, trangChu.class);
                startActivity(intent);
                finish();
            }
        });
    }
}