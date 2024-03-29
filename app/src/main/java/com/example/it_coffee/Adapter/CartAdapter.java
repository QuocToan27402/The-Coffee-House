package com.example.it_coffee.Adapter;

//import static com.example.it_coffee.CartActivity.totalPriceView;
//import static com.example.it_coffee.OrderActivity.order;

import static com.example.it_coffee.OrderActivity.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.it_coffee.CartActivity;
import com.example.it_coffee.Cart_EmptyActivity;
import com.example.it_coffee.DetailMenuActivity;
import com.example.it_coffee.Model.Order;
import com.example.it_coffee.Model.Product;
import com.example.it_coffee.R;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    Context context;
    int quantity = 1;
    List<Product> listProduct;
    Order order;
    public CartAdapter(Context context, List<Product> listProduct, Order order){
        this.context = context;
        this.listProduct = listProduct;
        this.order = order;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cartView = inflater.inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(cartView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = listProduct.get(position);
        quantity = p.getQuantityInCart();
        Glide.with(context)
                .load(p.getProductImageUrl())
                .into(holder.imageViewCart);

        holder.txtProductName.setText(p.getProductName());

        Locale locale = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setRoundingMode(RoundingMode.HALF_UP);
        holder.txtProductPrice.setText(format.format(p.getProductPrice()));

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity += 1;
                p.setQuantityInCart(quantity);
                order.addTotal(p);

                holder.txtQuantity.setText(String.valueOf(quantity));
            }
        });
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity -= 1;
                if(quantity < 1) {
                    quantity = 1;
                }
                p.setQuantityInCart(quantity);
                order.minusTotal(p);
                holder.txtQuantity.setText(String.valueOf(quantity));
            }
        });

        holder.txtQuantity.setText(String.valueOf(quantity));

        holder.btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listProduct.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
                order.removeProduct(p);
                if(order == null || order.getTotalPrice() == 0){
                    Intent intent = new Intent(context, Cart_EmptyActivity.class);
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewCart, btnDeleteProduct;
        TextView txtProductName, txtProductPrice, txtQuantity, btnPlus, btnMinus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCart = (ImageView) itemView.findViewById(R.id.imageCartItem);
            btnDeleteProduct = (ImageView) itemView.findViewById(R.id.btnDeleteCart);
            txtProductName = (TextView) itemView.findViewById(R.id.txtNameFoodCart);
            txtProductPrice = (TextView) itemView.findViewById(R.id.txtPriceFoodCart);
            txtQuantity = (TextView) itemView.findViewById(R.id.quantity_tv);
            btnMinus = (TextView) itemView.findViewById(R.id.minus_btn);
            btnPlus = (TextView) itemView.findViewById(R.id.plus_btn);
        }
    }
}
