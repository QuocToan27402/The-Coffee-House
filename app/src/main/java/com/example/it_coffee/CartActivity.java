package com.example.it_coffee;

import static com.example.it_coffee.OrderActivity.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.it_coffee.Adapter.CartAdapter;
import com.example.it_coffee.Model.Order;
import com.example.it_coffee.Model.Product;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {
    public static TextView totalPriceView;

    private RecyclerView rcvCart;
    private ImageView btnBack;
    private CartAdapter cartAdapter;
    private List<Product> productListInCart;
    private CardView btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getUI();
        productListInCart = new ArrayList<>();
        Order orderCart = order;
        if(order == null || order.getTotalPrice() == 0){
            Intent intent = new Intent(CartActivity.this, Cart_EmptyActivity.class);
            startActivity(intent);
        } else {
            productListInCart = orderCart.getCart();
            cartAdapter = new CartAdapter(this, productListInCart, orderCart);
            rcvCart.setLayoutManager(new LinearLayoutManager(this));
            rcvCart.setAdapter(cartAdapter);

            Locale locale = new Locale("vi", "VN");
            NumberFormat format = NumberFormat.getCurrencyInstance(locale);
            format.setRoundingMode(RoundingMode.HALF_UP);

            totalPriceView.setText(format.format(order.getTotalPrice()));
            onClickListener();
        }


        bottomNav();
    }

    private void onClickListener(){
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(order.getTotalPrice() > 0){
                    Intent intent = new Intent(CartActivity.this, OrderActivity.class);
                    Bundle bundle = new Bundle();
                    Order order1 = order;
                    bundle.putSerializable("order", order1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    public void getUI(){
        rcvCart = findViewById(R.id.rcvOrderItem);
        btnCheckout = findViewById(R.id.check_out_btn);
        totalPriceView = findViewById(R.id.txtTotal);
        btnBack = findViewById(R.id.cart_back_arrow);
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
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, CartActivity.class));
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, OrderHistoryActivity.class));
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, ProfileActivity.class));
            }
        });

    }
}