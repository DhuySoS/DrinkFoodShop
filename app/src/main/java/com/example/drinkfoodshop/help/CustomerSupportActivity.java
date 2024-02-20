package com.example.drinkfoodshop.help;
import com.example.drinkfoodshop.R;
import com.example.drinkfoodshop.adapter.FunctionAdapter;
import com.example.drinkfoodshop.domain.function;
import com.example.drinkfoodshop.home.trangChu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;

public class CustomerSupportActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FunctionAdapter functionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_support);

        TextView titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý khi nhấn vào thanh tiêu đề
                // Ví dụ: Chuyển đến trang chủ hoặc thực hiện các hành động khác
                Intent intent = new Intent(CustomerSupportActivity.this, trangChu.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<function> functions = new ArrayList<>();
        functions.add(new function("Câu Hỏi Thường Gặp 1", "Tiền đơn hàng bao gồm gì?"));
        functions.add(new function("Câu Hỏi Thường Gặp 2", "Làm sao để mua hàng?"));
        functions.add(new function("Câu Hỏi Thường Gặp 3", "Làm sao để đổi tài khoản?"));
        functions.add(new function("Câu Hỏi Thường Gặp 4", "Làm sao để xem thông tin người dùng?"));
        functions.add(new function("Câu Hỏi Thường Gặp 5", "Làm sao để đổi tên người dùng"));
        functions.add(new function("Hotline", "Gọi đến Hotline"));

        functionAdapter = new FunctionAdapter(functions, new FunctionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        // Hiển thị câu trả lời cho câu hỏi thường gặp 1
                        showAnswer("Tiền hàng và tiền thuế.");
                        break;
                    case 1:
                        // Hiển thị câu trả lời cho câu hỏi thường gặp 2
                        showAnswer("Tìm và chọn sản phẩm mong muốn. Sau đó chọn số lượng rồi thêm vào giỏ hàng. Về màn hình chính rồi bấm biểu tượng giỏ hàng sau đó tiến hành thanh toán.");
                        break;
                    case 2:
                        // Hiển thị câu trả lời cho câu hỏi thường gặp 3
                        showAnswer("Vào cài đặt sau đó bấm vào đăng xuất để tiến hành đổi tài khoản.");
                        break;
                    case 3:
                        // Hiển thị câu trả lời cho câu hỏi thường gặp 4
                        showAnswer("2 cách: 1 bấm vào phần cá nhân, 2 bấm vào ảnh ở góc phải trên cùng ở trang chủ.");
                        break;
                    case 4:
                        // Hiển thị câu trả lời cho câu hỏi thường gặp 5
                        showAnswer("Vào cài đặt sau đó nhập tên và bấm nút đổi tên để tiến hành đổi tên người dùng.");
                        break;
                    case 5:
                        // Gọi đến số hotline
                        dialHotline("0395254390");
                        break;
                }
            }
        });
        recyclerView.setAdapter(functionAdapter);
    }

    private void showAnswer(String answer) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Câu trả lời");
        builder.setMessage(answer);
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    private void dialHotline(String phoneNumber) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        startActivity(dialIntent);
    }
}
