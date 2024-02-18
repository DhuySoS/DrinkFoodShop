package com.example.drinkfoodshop.saveHistory;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.domain.food;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class YourHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private TextView tvPhoneNumber, tvAddress, tvPaymentMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_history);

        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvAddress = findViewById(R.id.tvAddress);
        tvPaymentMethod = findViewById(R.id.tvPaymentMethod);

        // Lấy thông tin từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("OrderInfo", MODE_PRIVATE);
        String phoneNumber = sharedPreferences.getString("phoneNumber", "");
        String address = sharedPreferences.getString("address", "");
        String paymentMethod = sharedPreferences.getString("paymentMethod", "");

        // Hiển thị thông tin
        tvPhoneNumber.setText("Số điện thoại: " + phoneNumber);
        tvAddress.setText("Địa chỉ: " + address);
        tvPaymentMethod.setText("Phương thức thanh toán: " + paymentMethod);

        // Lấy danh sách món ăn từ SharedPreferences
        String jsonCartList = sharedPreferences.getString("cartList", "");
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
        }
    }
}
