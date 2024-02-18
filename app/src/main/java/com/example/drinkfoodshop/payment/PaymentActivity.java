package com.example.drinkfoodshop.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.cart.cart;
import com.example.drinkfoodshop.domain.food;
import com.example.drinkfoodshop.help.ManagmentCart;
import com.example.drinkfoodshop.saveHistory.YourHistoryActivity;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    private EditText phoneNumberEditText;
    private EditText addressEditText;
    private RadioGroup paymentMethodRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber);
        addressEditText = findViewById(R.id.editTextAddress);
        paymentMethodRadioGroup = findViewById(R.id.radioGroupPaymentMethod);

        Button confirmPaymentButton = findViewById(R.id.buttonConfirmPayment);

        confirmPaymentButton.setOnClickListener(v -> {
            String phoneNumber = phoneNumberEditText.getText().toString();
            String address = addressEditText.getText().toString();

            // Check if a payment method is selected
            if (paymentMethodRadioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Vui lòng chọn phương thức thanh toán", Toast.LENGTH_SHORT).show();
            } else {
                // Get the selected payment method
                RadioButton selectedPaymentMethod = findViewById(paymentMethodRadioGroup.getCheckedRadioButtonId());
                String paymentMethod = selectedPaymentMethod.getText().toString();

                // Lưu thông tin vào SharedPreferences và quản lý giỏ hàng
                saveOrderInfoToSharedPreferences(phoneNumber, address, paymentMethod);

                // Xóa giỏ hàng
                ManagmentCart managmentCart = new ManagmentCart(this);
                managmentCart.clearCart();

                // Chuyển sang trang hỗ trợ (hoặc trang mong muốn)
                Intent intent = new Intent(PaymentActivity.this, cart.class);
                startActivity(intent);

                // Kết thúc activity
                finish();
            }
        });
    }

    private void saveOrderInfoToSharedPreferences(String phoneNumber, String address, String paymentMethod) {
        // Lưu thông tin vào SharedPreferences và quản lý giỏ hàng
        ManagmentCart managmentCart = new ManagmentCart(this);
        managmentCart.saveFoodInfo(phoneNumber, address, paymentMethod);
    }
}
