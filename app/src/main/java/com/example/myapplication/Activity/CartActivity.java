package com.example.myapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Adapter.CartAdapter;
import com.example.myapplication.Helper.ChangeNumberItemsListener;
import com.example.myapplication.Helper.ManagementCart;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityCartBinding;

import java.text.DecimalFormat;

public class CartActivity extends BaseActivity {
    ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagementCart managementCart;
    private double tax;
    private Context context;
    TextView txtgiamgia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managementCart = new ManagementCart(this);
        setVariable();
        calculateCart();
        initList();
        Thamchieu();
    }

    private void Thamchieu() {
        txtgiamgia = findViewById(R.id.txtgiamgia);
    }

    private void initList() {
        if(managementCart.getListCart().isEmpty()){
            binding.txtEmpty.setVisibility(View.VISIBLE);
            binding.scrlViewCart.setVisibility(View.GONE);
        }
        else{
            binding.txtEmpty.setVisibility(View.GONE);
            binding.scrlViewCart.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.cardView.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });
        binding.cardView.setAdapter(adapter);
    }

    private void calculateCart() {
        double khongdong = 0;
        double percentTax = 0.02; //percent 2% tax
        double delivery = 30000; //2000 VND
        double giam = 0.2;// 20%
        double songthuyen = 30000;
        double hoangquyphi = 0.2;
        double dagiam = Math.round(managementCart.getTotalFee()* giam );
        double masongthuyen = Math.round(songthuyen);
        double mahoangquyphi = Math.round(tax);
        double tax = Math.round(managementCart.getTotalFee() * percentTax * 100.0) / 100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery ) * 100) / 100;
        double itemTotal = Math.round(managementCart.getTotalFee());

// Định dạng lại giá tiền
        DecimalFormat currencyFormat = new DecimalFormat("###,###,### VNĐ");

// Đặt giá trị vào các TextViews
        String GIAMGIA = new String();
        binding.txtTotalFee.setText(currencyFormat.format(itemTotal));
        binding.txtDelivery.setText(currencyFormat.format(delivery));
        binding.txtTax.setText(currencyFormat.format(tax));
        binding.txtTotal.setText(currencyFormat.format(total));

        binding.btnSuDung.setOnClickListener(new View.OnClickListener() {
            private String getText(EditText edtNhapVoucher) {
                String giamgia = edtNhapVoucher.getText().toString().trim();
                return giamgia;
            }

            @Override
            public void onClick(View v) {
                String giamgia = getText(binding.edtNhapVoucher);
                if (giamgia.equals("GIAMGIA")) {
                    binding.txtgiamgia.setText(currencyFormat.format(dagiam));
                    double total3 = Math.round(managementCart.getTotalFee() - dagiam + delivery + tax);
                    binding.txtTotal.setText(currencyFormat.format(total3));
                    Toast.makeText(getApplicationContext(), "Áp dụng mã giảm giá thành công", Toast.LENGTH_SHORT).show();
                } else if (giamgia.equals("SONGTHUYEN")) {
                    binding.txtDelivery.setText(currencyFormat.format(khongdong));
                    binding.txtgiamgia.setText(currencyFormat.format(songthuyen));
                    double total3 = Math.round(managementCart.getTotalFee() - masongthuyen  + tax);
                    binding.txtTotal.setText(currencyFormat.format(total3));
                    Toast.makeText(getApplicationContext(), "Song Thuyên sẽ ship hàng cho bạn", Toast.LENGTH_SHORT).show();
                } else if (giamgia.equals("HOANGQUYPHI")) {
                    binding.txtTax.setText(currencyFormat.format(khongdong));
                    binding.txtgiamgia.setText(currencyFormat.format(tax));
                    double total3 = Math.round(managementCart.getTotalFee() + delivery );
                    binding.txtTotal.setText(currencyFormat.format(total3));
                    Toast.makeText(getApplicationContext(), "Áp dụng thành công mã giảm VAT", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Mã giảm giá không có hoặc không hợp lệ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        binding.btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalAmountFormatted = binding.txtTotal.getText().toString();
                Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
                intent.putExtra("total_amount", totalAmountFormatted);
                startActivity(intent);
            }
        });
    }

    private void setVariable() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}