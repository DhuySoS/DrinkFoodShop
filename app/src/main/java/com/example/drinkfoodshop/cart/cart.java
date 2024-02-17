package com.example.drinkfoodshop.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinkfoodshop.adapter.CartAdapter;
import com.example.drinkfoodshop.databinding.ActivityCartBinding;
import com.example.drinkfoodshop.help.ChangeNumberItemsListener;
import com.example.drinkfoodshop.help.ManagmentCart;
import com.example.drinkfoodshop.home.trangChu;
import com.example.drinkfoodshop.payment.PaymentActivity;

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
//        LinearLayoutManager linearLayoutManager=   new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        binding.cardView.setLayoutManager(linearLayoutManager);
        binding.cardView.setLayoutManager(new LinearLayoutManager(this));
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

    private void setVariable() {
        binding.btnDatHang.setOnClickListener(v -> {
            Intent intent = new Intent(cart.this, PaymentActivity.class);
            startActivity(intent);
        });
        binding.Cart.setOnClickListener(v -> {
            Intent intent = new Intent(cart.this, trangChu.class);
            startActivity(intent);
            finish(); // Kết thúc activity hiện tại để không quay lại khi nhấn nút Back
        });
    }
}
