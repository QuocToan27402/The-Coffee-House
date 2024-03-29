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
import com.example.it_coffee.Model.Category;
import com.example.it_coffee.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<Category> list;
    Context context;
    public CategoryAdapter(List<Category> list){
        this.list = list;
    }
    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView = inflater.inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(categoryView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Category temp = list.get(position);
        Glide.with(context)
                .load(temp.getCategoryImageUrl())
                .into(holder.imgViewCate);
//        int imageId =  context.getResources().getIdentifier(temp.categoryImage, "drawable", context.getPackageName());
//        if(imageId != 0)
//            holder.imgViewCate.setImageResource(imageId);
        holder.txtCateName.setText(temp.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgViewCate;
        TextView txtCateName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewCate = (ImageView) itemView.findViewById(R.id.imageViewCate);
            txtCateName = (TextView) itemView.findViewById(R.id.txtCateName);
        }
    }
}
