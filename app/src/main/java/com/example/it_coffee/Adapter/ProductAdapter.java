package com.example.it_coffee.Adapter;

import static com.example.it_coffee.CartActivity.totalPriceView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.it_coffee.DetailMenuActivity;
import com.example.it_coffee.Model.Product;
import com.example.it_coffee.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> list;
    Context context;
    private FirebaseStorage storage;
    public ProductAdapter(Context context, List<Product> list) {
        storage = FirebaseStorage.getInstance();
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View menuView = inflater.inflate(R.layout.viewholder_menu, parent, false);
        return new ViewHolder(menuView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product p = list.get(position);

        Glide.with(context)
                .load(p.getProductImageUrl())
                .into(holder.imageFood);

        holder.txtName.setText(p.getProductName());

        Locale locale = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setRoundingMode(RoundingMode.HALF_UP);

//        totalPriceView.setText(format.format(totalPrice));

        holder.txtPrice.setText(format.format(p.getProductPrice()));


        holder.productId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailMenuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("detail_menu", p);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtPrice;
        ImageView imageFood;
        ConstraintLayout productId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtNameFood);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            imageFood = (ImageView) itemView.findViewById(R.id.imageFood);
            productId = (ConstraintLayout) itemView.findViewById(R.id.menuId);

        }
    }
}
