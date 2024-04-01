package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.myapplication.Domain.Phones;
import com.example.myapplication.Helper.ChangeNumberItemsListener;
import com.example.myapplication.Helper.ManagementCart;
import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewholder> {
    ArrayList<Phones> list;
    private ManagementCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdapter(ArrayList<Phones> list, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.list = list;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new viewholder(inflate);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.viewholder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,### VNĐ");
        String formattedFee = decimalFormat.format(list.get(position).getNumberInCart() * list.get(position).getPrice());
        String formattedTotal = decimalFormat.format(list.get(position).getNumberInCart() * list.get(position).getPrice());

// Đặt giá trị vào TextView
        holder.feeEachItem.setText(formattedFee);
        holder.totalEachItem.setText(list.get(position).getNumberInCart() + " x " + formattedTotal);
        holder.num.setText(list.get(position).getNumberInCart()+"");

        if (list.get(position).getImagePath() != null && holder.itemView.getContext() != null && holder.picgh != null) {
            Glide.with(holder.itemView.getContext())
                    .load(list.get(position).getImagePath())
                    .transform(new CenterCrop(), new RoundedCorners(30))
                    .into(holder.picgh);
        } else {

        }


        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberItem(list, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusNumberItem(list, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem, plusItem, minusItem, totalEachItem, num;
        ImageView picgh;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txtTitle);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            plusItem = itemView.findViewById(R.id.btnPlusCart);
            minusItem = itemView.findViewById(R.id.btnMinusCart);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItem);
            picgh = itemView.findViewById(R.id.picgh);

        }
    }
}
