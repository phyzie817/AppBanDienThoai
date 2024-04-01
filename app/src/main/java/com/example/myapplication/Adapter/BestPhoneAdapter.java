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
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.myapplication.Activity.DetailActivity;
import com.example.myapplication.Domain.Phones;
import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BestPhoneAdapter extends RecyclerView.Adapter<BestPhoneAdapter.viewholder>{
    private final ArrayList<Phones> items;
    ArrayList<Phones> item;
    Context context;

    public BestPhoneAdapter(ArrayList<Phones> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BestPhoneAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_best_deal, parent,false );
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BestPhoneAdapter.viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtTitle.setText(items.get(position).getTitle());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,### VNĐ");
        String formattedPrice = decimalFormat.format(items.get(position).getPrice());

// Đặt giá trị vào TextView
        holder.txtPrice.setText(formattedPrice);
        holder.txtTime.setText(items.get(position).getTimeValue()+" min");
        holder.txtStar.setText(""+items.get(position).getStar());

        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object", items.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtPrice, txtStar, txtTime;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtPrice = itemView.findViewById(R.id.totalEachItem);
            txtStar = itemView.findViewById(R.id.txtStar);
            txtTime = itemView.findViewById(R.id.txtTime);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
