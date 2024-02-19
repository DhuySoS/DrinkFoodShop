package com.example.drinkfoodshop.saveHistory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinkfoodshop.Profile1.Profile1;
import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.domain.food;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class YourHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private TextView tvPhoneNumber, tvAddress, tvPaymentMethod, tvTotal, backTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_history);

        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvAddress = findViewById(R.id.tvAddress);
        tvPaymentMethod = findViewById(R.id.tvPaymentMethod);
        tvTotal = findViewById(R.id.tvTotal);
        backTitle = findViewById(R.id.historyTitle);
        //back về trang profile
        backTitle.setOnClickListener(v -> {
            Intent intent = new Intent(YourHistoryActivity.this, Profile1.class);
            startActivity(intent);
        });
        // Lấy thông tin từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("OrderInfo", MODE_PRIVATE);
        String phoneNumber = sharedPreferences.getString("phoneNumber", "");
        String address = sharedPreferences.getString("address", "");
        String paymentMethod = sharedPreferences.getString("paymentMethod", "");
        String total = sharedPreferences.getString("total", "");
        // Hiển thị thông tin
        tvPhoneNumber.setText("Số điện thoại: " + phoneNumber);
        tvAddress.setText("Địa chỉ: " + address);
        tvPaymentMethod.setText("Phương thức thanh toán: " + paymentMethod);
        tvTotal.setText("Tổng đơn hàng: "+ total +" VNĐ");

        // Lấy danh sách món ăn từ SharedPreferences
        String jsonCartList = sharedPreferences.getString("orderHistory", "");
        if (!jsonCartList.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<food>>() {
            }.getType();
            ArrayList<food> cartList = gson.fromJson(jsonCartList, type);

            // Hiển thị danh sách món ăn
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            historyAdapter = new HistoryAdapter(cartList);
            recyclerView.setAdapter(historyAdapter);
        }else {
            Toast.makeText(this, "Danh sách rỗng", Toast.LENGTH_SHORT).show();
        }
    }
}
