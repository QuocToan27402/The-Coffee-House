package com.example.it_coffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class OrderHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        bottomNav();
    }
    private void bottomNav(){
        LinearLayout btnHome, btnCart, btnOrder, btnProfile;
        btnHome = findViewById(R.id.homeBtn);
        btnCart = findViewById(R.id.cartBtn);
        btnOrder = findViewById(R.id.orderBtn);
        btnProfile = findViewById(R.id.profileBtn);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderHistoryActivity.this, MainActivity.class));
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderHistoryActivity.this, CartActivity.class));
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderHistoryActivity.this, OrderHistoryActivity.class));
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderHistoryActivity.this, ProfileActivity.class));
            }
        });

    }
}