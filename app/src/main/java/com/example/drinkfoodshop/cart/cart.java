package com.example.drinkfoodshop.cart;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinkfoodshop.databinding.ActivityCartBinding;
import com.example.drinkfoodshop.help.ManagmentCart;

public class cart extends AppCompatActivity {
    private ActivityCartBinding  binding;
    private RecyclerView.Adapter apdapter;
    private ManagmentCart managmentCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setVariable();


    }
    private void setVariable(){

    }
    // vu da day
}