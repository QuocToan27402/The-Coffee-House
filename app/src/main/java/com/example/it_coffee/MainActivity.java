package com.example.it_coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it_coffee.Adapter.CategoryAdapter;
import com.example.it_coffee.Adapter.ProductAdapter;
import com.example.it_coffee.Model.Category;
import com.example.it_coffee.Model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvCategory, rcvMenu;
    private List<Category> listCategory;
    private List<Product> listProduct;
    private ProductAdapter productAdapter;
    private  CategoryAdapter categoryAdapter;

    FirebaseAuth auth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //RecyclerView Category


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            rcvCategory = (RecyclerView) findViewById(R.id.rcvCategory);
            listCategory = new ArrayList<>();
            getCategoryFromFireBase();
            categoryAdapter = new CategoryAdapter(listCategory);
            rcvCategory.setAdapter(categoryAdapter);
            rcvCategory.setLayoutManager(new LinearLayoutManager(this,  LinearLayoutManager.HORIZONTAL, false));


            //RecyclerView Food
            rcvMenu = (RecyclerView) findViewById(R.id.rcvMenu);
            listProduct = new ArrayList<>();
            getProductFromFireBase();
            productAdapter = new ProductAdapter(this, listProduct);
            rcvMenu.setAdapter(productAdapter);
            rcvMenu.setLayoutManager(new GridLayoutManager(this,  2));

            bottomNav();
        }


    }
    private void getCategoryFromFireBase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference("Category");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Category category = dataSnapshot.getValue(Category.class);
                    listCategory.add(category);
                }
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Lỗi khi lấy category từ firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProductFromFireBase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference("Product");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    listProduct.add(product);
                }
//                productAdapter.notifyDataSetChanged();
                productAdapter.notifyItemChanged(listProduct.size()-1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Lỗi khi lấy product từ firebase", Toast.LENGTH_SHORT).show();
            }
        });
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
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OrderHistoryActivity.class));
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

    }
}