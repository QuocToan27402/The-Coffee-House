package com.example.it_coffee;

import static com.example.it_coffee.OrderActivity.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.it_coffee.Model.Order;
import com.example.it_coffee.Model.Product;
import com.example.it_coffee.Model.User;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class DetailMenuActivity extends AppCompatActivity {
    private TextView txtProductName, txtProductPrice, txtQuantity, btnPlus, btnMinus;
    private ImageView imageViewProduct;
    private CardView btnAddToCart;
    int quantity = 1;
    Product product;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);
        Bundle bundle = getIntent().getExtras();
        product = (Product) bundle.get("detail_menu");

        initUi();
        onClick();
        Glide.with(this)
                .load(product.getProductImageUrl())
                .into(imageViewProduct);
        txtProductName.setText(product.getProductName());


        Locale locale = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setRoundingMode(RoundingMode.HALF_UP);
        txtProductPrice.setText(format.format(product.getProductPrice()));
    }

    private void initUi(){
        txtProductName = findViewById(R.id.product_name);
        txtProductPrice = findViewById(R.id.product_price);
        imageViewProduct = findViewById(R.id.product_img);
        txtQuantity = findViewById(R.id.quantity_tv);
        btnMinus = findViewById(R.id.minus_btn);
        btnPlus = findViewById(R.id.plus_btn);
        btnAddToCart = findViewById(R.id.add_to_cart_btn);
    }

    private void onClick(){
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity += 1;
                txtQuantity.setText(String.valueOf(quantity));
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity -= 1;
                if(quantity < 0){
                    quantity = 0;
                }
                txtQuantity.setText(String.valueOf(quantity));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInCart = false;
                if(order == null){
                    order = new Order();
                } else {
                    for (int i = 0; i < order.getCart().size(); i++) {
                        if(product.getProductId().equals(order.getCart().get(i).getProductId())){
                            isInCart = true;
                            break;
                        }
                    }
                }
                if(!isInCart){
                    product.setQuantityInCart(quantity);
                    order.addProductToCart(product);
                    Toasty.success(DetailMenuActivity.this,"Added to cart",Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    Toasty.warning(DetailMenuActivity.this,"Already in cart",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void goBack(View view) {
        finish();
    }

//    public interface CartListener{
//        public void onClickAddToCart(Product p);
//        public void onClickUpdateToCart(Product p);
//        public void onClickRemoveToCart(Product p);
//    }
}