package com.example.myapplication.Adapter;
import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.myapplication.Activity.DetailActivity;
import com.example.myapplication.Activity.LoginActivity;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Activity.UpdatePhoneActivity;
import com.example.myapplication.Domain.Phones;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PhoneListAdapter extends RecyclerView.Adapter<PhoneListAdapter.viewholder> {
    ArrayList<Phones> items;
    Context context;

    public PhoneListAdapter(ArrayList<Phones> items){
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public PhoneListAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.viewholder_phone_list, parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneListAdapter.viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtTitle.setText(items.get(position).getTitle());
        holder.txtPrice.setText(items.get(position).getPrice() + "VNĐ");
        holder.txtTime.setText(items.get(position).getTimeValue() + " min");
        holder.txtRate.setText("" + items.get(position).getStar());


        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);


        // chạm vào button để xóa điện thoại sẽ được hiển thị. Khi kết thúc chạm, nó sẽ ẩn nút đó.
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Xử lý khi bắt đầu chạm vào
                        holder.btnDelPhone.setVisibility(View.VISIBLE); // Hiển thị button xóa khi bắt đầu chạm
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // Xử lý khi bắt đầu chạm vào
                        holder.btnDelPhone.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_UP:
                        // Xử lý khi kết thúc chạm
                        holder.btnDelPhone.setVisibility(View.GONE); // Ẩn button xóa khi kết thúc chạm
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        holder.btnDelPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = String.valueOf(getItemId(position)); // Giả sử getItemId() trả về khóa của mục dữ liệu cần xóa

                FirebaseDatabase.getInstance().getReference()
                        .child("Phones").child(key).removeValue()  //xóa node được chọn từ cơ sở dữ liệu.
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public long getItemId(int position) {
        // Giả định bạn có một danh sách các đối tượng Phones có phương thức getId() để trả về ID của mỗi đối tượng.
        return items.get(position).getId();
    }

    //trả về số lượng item trong danh sách.
    @Override
    public int getItemCount() {
        return items.size();
    }


    //chứa các thành phần giao diện người dùng mà mỗi item trong RecyclerView sẽ có
    public class viewholder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtPrice, txtRate, txtTime;
        ImageView pic;
        Button btnUpdatePhone, btnDelPhone;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtRate = itemView.findViewById(R.id.txtRate);
            txtTime = itemView.findViewById(R.id.txtTime);
            pic = itemView.findViewById(R.id.imageView5);
            btnDelPhone = itemView.findViewById(R.id.btnDelPhone);
        }
    }
}
