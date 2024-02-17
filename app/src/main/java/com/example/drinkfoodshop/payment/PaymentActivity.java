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
import com.example.drinkfoodshop.help.ManagmentCart;

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

                // Display a success message
                Toast.makeText(this, "Thanh toán thành công bằng " + paymentMethod, Toast.LENGTH_SHORT).show();

                // Clear the cart (Assuming you have a method to clear the cart in the ManagmentCart class)
                ManagmentCart managmentCart = new ManagmentCart(this);
                managmentCart.clearCart();

                // Navigate back to the home screen
                Intent intent = new Intent(PaymentActivity.this, cart.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear the stack, so pressing back won't go back to PaymentActivity
                startActivity(intent);
                finish();
            }
        });
    }
}