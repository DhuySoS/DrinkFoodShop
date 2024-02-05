package com.example.drinkfoodshop.Profile1;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvLogOut;
    private FirebaseAuth mAuth;
    private ImageView Back;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile1);
//        mAuth = FirebaseAuth.getInstance();
//        tvLogOut = findViewById(R.id.textView105);
//        Back = findViewById(R.id.imgBack);
//// function
//        onclick();
//    }
//
//    private void onclick() {
//        tvLogOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(ProfileActivity.this, intro.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        Back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ProfileActivity.this, trangChu.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }

}