package com.example.it_coffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.it_coffee.Model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    private TextView nameTextView, phoneTextView,addressTextView, txtLogout;
    private ImageView userImageView;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameTextView =findViewById(R.id.textName);
        phoneTextView = findViewById(R.id.textView_ShowPhone);
        addressTextView = findViewById(R.id.textView_ShowAddress);
        userImageView = findViewById(R.id.imageProfile);
        txtLogout = findViewById(R.id.logoutNow);
        bottomNav();

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference().child("Users");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                finish();
            }
        });
        userRef.child(user.getUid()).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                User user1 = (User) dataSnapshot.getValue(User.class);
                nameTextView.setText(user1.getFullname());
                phoneTextView.setText(String.valueOf(user1.getPhonenumber()));
                addressTextView.setText(user1.getAddress());
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
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, CartActivity.class));
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, OrderHistoryActivity.class));
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
            }
        });

    }
}