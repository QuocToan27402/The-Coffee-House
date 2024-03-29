package com.example.it_coffee.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.it_coffee.Model.Order;
import com.example.it_coffee.Model.Product;
import com.example.it_coffee.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    Context context;
    List<Product> listProduct;
    Order order;
    public OrderAdapter(List<Product> listProduct){
        this.listProduct = listProduct;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View orderView = inflater.inflate(R.layout.viewholder_item_order, parent, false);
        return new ViewHolder(orderView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = listProduct.get(position);
        Glide.with(context)
                .load(p.getProductImageUrl())
                .into(holder.imageViewCart);

        holder.txtProductName.setText(p.getProductName());
        holder.txtProductPrice.setText(String.valueOf(p.getProductPrice()));
        holder.txtQuantity.setText("x" + String.valueOf(p.getQuantityInCart()));
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewCart;
        TextView txtProductName, txtProductPrice, txtQuantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCart = (ImageView) itemView.findViewById(R.id.imageCartItem);
            txtProductName = (TextView) itemView.findViewById(R.id.txtNameFoodCart);
            txtProductPrice = (TextView) itemView.findViewById(R.id.txtPriceFoodCart);
            txtQuantity = (TextView) itemView.findViewById(R.id.txtQuantity);
        }
    }
}
