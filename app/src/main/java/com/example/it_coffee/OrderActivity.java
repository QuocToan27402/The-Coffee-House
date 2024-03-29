package com.example.it_coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it_coffee.Adapter.OrderAdapter;
import com.example.it_coffee.Model.Order;
import com.example.it_coffee.Model.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class OrderActivity extends AppCompatActivity {
    public static Order order;
    private ImageView btnBack;
    private RecyclerView rcvOrder;
    private TextView totalPrice;
    private EditText txtComment, txtAddress;
    private CardView btnConfirmOrder;

    private List<Product> productList;
    private OrderAdapter orderAdapter;
    private String address, comment;
    Order order1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getUI();

        Bundle bundle = getIntent().getExtras();
        order1 = new Order();
        if(bundle.get("order") == null) {
            return;
        }
        order1 = (Order) bundle.get("order");

        productList = order1.getCart();

        orderAdapter = new OrderAdapter(productList);
        rcvOrder.setAdapter(orderAdapter);
        rcvOrder.setLayoutManager(new LinearLayoutManager(this));


        Locale locale = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setRoundingMode(RoundingMode.HALF_UP);

        totalPrice.setText(format.format(order.getTotalPrice()));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        onClickLisener();


//        bottomNav();
    }

    private void onClickLisener(){
        btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address = txtAddress.getText().toString();
                comment = txtComment.getText().toString();

                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Order");
                String key = myRef.push().getKey();
                order1.setOrderId(key);

                String currentDate = DateFormat.getDateInstance().format(new Date());
                order1.setOrderDate(currentDate);

                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                order1.setCustomerId(user.getUid());
                order1.setAddress(address);
                order1.setComments(comment);
                order1.setProductList(productList);
                order1.setTotalPrice(order.getTotalPrice());

                myRef.child(key).setValue(order1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        order.getCart().clear();
                        order = null;
                        Toasty.success(OrderActivity.this, "Order Successfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(OrderActivity.this, OrderSuccessActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toasty.error(OrderActivity.this, "Order Thất bại", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void getUI(){
        rcvOrder = findViewById(R.id.rcvOrderItem);
        btnConfirmOrder = findViewById(R.id.check_out_btn);
        totalPrice = findViewById(R.id.txtTotal);
        btnBack = findViewById(R.id.cart_back_arrow);
        txtAddress = findViewById(R.id.checkout_address_view);
        txtComment = findViewById(R.id.checkout_comment_view);
    }

}