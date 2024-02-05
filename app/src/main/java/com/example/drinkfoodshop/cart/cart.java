package com.example.drinkfoodshop.cart;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinkfoodshop.adapter.CartAdapter;
import com.example.drinkfoodshop.databinding.ActivityCartBinding;
import com.example.drinkfoodshop.help.ChangeNumberItemsListener;
import com.example.drinkfoodshop.help.ManagmentCart;

public class cart extends AppCompatActivity {
    private ActivityCartBinding  binding;
    private RecyclerView.Adapter apdapter;
    private ManagmentCart managmentCart;
    private double tax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        managmentCart=new ManagmentCart(this);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setVariable();
        caculateCart();
        intiList();


    }

    private void intiList() {
        if(managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollView3.setVisibility(View.GONE);

        }
        else{
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollView3.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager linearLayoutManager=   new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.cardView.setLayoutManager(linearLayoutManager);
        apdapter=new CartAdapter(managmentCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                caculateCart();
            }
        });
        binding.cardView.setAdapter(apdapter);
    }


    private void caculateCart() {
        double percenttax=0.02;
        double delivery=15000;
        tax=Math.round(managmentCart.getTotalFee()*percenttax*100)/100;
        double total=Math.round(managmentCart.getTotalFee() +tax+delivery);
        double itemTotal=Math.round(managmentCart.getTotalFee()*100)/100;

        binding.tongtxt.setText(itemTotal+"VND ");
        binding.thuetxt.setText(tax+"VND ");
        binding.shiptxt.setText(delivery+" ");
        binding.tongtxt.setText(total+ "VND");


    }

    private void setVariable(){
        binding.backBtn.setOnClickListener(v->finish());

    }
    // vu da day
}