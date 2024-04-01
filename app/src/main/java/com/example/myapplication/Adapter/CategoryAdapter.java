package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Activity.PhoneListActivity;
import com.example.myapplication.Domain.Category;
import com.example.myapplication.Domain.Phones;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {
    ArrayList<Category> items;
    Context context;

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent,false );
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtTitle.setText(items.get(position).getName());

        switch (position){
            case 0:{
                holder.pic.setBackgroundResource(R.drawable.cat1_bkg);
                break;
            }

            case 1:{
                holder.pic.setBackgroundResource(R.drawable.cat2_bkg);
                break;
            }

            case 2:{
                holder.pic.setBackgroundResource(R.drawable.cat3_bkg);
                break;
            }

            case 3:{
                holder.pic.setBackgroundResource(R.drawable.cat4_bkg);
                break;
            }

            case 4:{
                holder.pic.setBackgroundResource(R.drawable.cat5_bkg);
                break;
            }

            case 5:{
                holder.pic.setBackgroundResource(R.drawable.cat6_bkg);
                break;
            }
            case 6:{
                holder.pic.setBackgroundResource(R.drawable.cat7_bkg);
                break;
            }
            case 7:{
                holder.pic.setBackgroundResource(R.drawable.cat8_bkg);
                break;
            }
        }
        int drawableResourceId = context.getResources().getIdentifier(items.get(position).getImagePath(),
                "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int categoryId = items.get(position).getId(); // Sửa lại đây
                String categoryName = items.get(position).getName(); // Sửa lại đây

                Intent intent = new Intent(context, PhoneListActivity.class);
                intent.putExtra("CategoryId", categoryId);
                intent.putExtra("CategoryName", categoryName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtCatName);
            pic = itemView.findViewById(R.id.imgCat);
        }
    }
}



