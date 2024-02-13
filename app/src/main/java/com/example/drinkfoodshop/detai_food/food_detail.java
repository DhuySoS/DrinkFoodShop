package com.example.drinkfoodshop.detai_food;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.drinkfoodshop.databinding.ActivityFoodDetailBinding;
import com.example.drinkfoodshop.domain.categoryDomain;
import com.example.drinkfoodshop.domain.food;
import com.example.drinkfoodshop.help.ManagmentCart;

public class food_detail extends AppCompatActivity {
    ActivityFoodDetailBinding binding;
    private int num =0;
    private food object;
    private ManagmentCart managmentCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        managmentCart=new ManagmentCart(this);
        binding.backBtn.setOnClickListener(v -> finish());

        if (object != null) {
            Glide.with(food_detail.this)
                    .load(object.getImagePath())
                    .into(binding.Pic);
            binding.Pricetxt.setText(object.getPrice() + " VNĐ");
            binding.titleTxt.setText(object.getTitle());
            binding.DescriptionTxt.setText(object.getDescription());
            binding.totalTxt.setText((num * object.getPrice() + " VNĐ"));
        }
        binding.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num=num+1;
                binding.numtxt.setText(num+" ");
                binding.totalTxt.setText((num*object.getPrice())+" VNĐ");

            }
        });
        binding.minuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num > 0){
                    num=num-1;
                    binding.numtxt.setText(num+"");
                    binding.totalTxt.setText((num*object.getPrice())+" VNĐ");

                }
            }
        });
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(num);
                managmentCart.insertFood(object);
            }
        });
    }


    private void getIntentExtra() {
        object =(food) getIntent().getSerializableExtra("object");
        // tao đã sửa
    }
}