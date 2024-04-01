package com.example.myapplication.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.myapplication.Domain.Phones;
import com.example.myapplication.Helper.ManagementCart;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityDetailBinding;

import java.text.DecimalFormat;

public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding;
    private Phones object;
    private int num = 1;

    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        getIntentExtra();
        setVariable();

    }


    private void setVariable() {
        managementCart = new ManagementCart(this);
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, CartActivity.class));
            }
        });

        Glide.with(DetailActivity.this).load(object.getImagePath())
                .into(binding.imgSp);

        binding.txtTitle.setText(object.getTitle());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,### VNĐ");
        String formattedPrice = decimalFormat.format(object.getPrice());
        binding.txtPrice.setText(formattedPrice);
        binding.txtDescription.setText(object.getDescription());
        binding.txtRate.setText(object.getStar()+" ");
        binding.ratingBar.setRating((float) object.getStar());

        binding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num + 1;
                binding.tvNum.setText(num + " ");
                binding.txtTotal.setText(decimalFormat.format(num * object.getPrice()));
            }
        });

        binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num > 1) { // Đảm bảo số lượng không âm
                    num = num - 1;
                    binding.tvNum.setText(num+"");
                    binding.txtTotal.setText(decimalFormat.format(num * object.getPrice()));
                }
            }
        });
        binding.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalAmountFormatted = binding.txtTotal.getText().toString();
                Intent intent = new Intent(DetailActivity.this, PaymentActivity.class);
                intent.putExtra("total_amount", totalAmountFormatted);
                startActivity(intent);
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(num);
                managementCart.insertPhone(object);
            }
        });
    }

    private void getIntentExtra() {
        object = (Phones) getIntent().getSerializableExtra("object");
    }
}