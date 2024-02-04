package com.example.drinkfoodshop.detai_food;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.drinkfoodshop.databinding.ActivityFoodDetailBinding;
import com.example.drinkfoodshop.domain.categoryDomain;

public class food_detail extends AppCompatActivity {
    ActivityFoodDetailBinding binding;
    private int num =1;
    private categoryDomain object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());

        if (object != null) {
            Glide.with(food_detail.this)
                    .load(object.getPic())
                    .into(binding.Pic);
            binding.Pricetxt.setText("$" + object.getPrice());
            binding.titleTxt.setText(object.getTitle());
            binding.DescriptionTxt.setText(object.getDescription());
            binding.totalTxt.setText((num * object.getPrice() + "$"));
        } else {
            // Xử lý khi object là null
            // Có thể hiển thị thông báo hoặc thực hiện các hành động phù hợp.
        }
    }

    private void getIntentExtra() {
        object =(categoryDomain) getIntent().getSerializableExtra("object");
        // tao đã sửa
    }
}