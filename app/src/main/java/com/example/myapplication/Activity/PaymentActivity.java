package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityPaymentBinding;

import java.text.DecimalFormat;

public class PaymentActivity extends BaseActivity {
    ActivityPaymentBinding binding;
    TextView txtTotal;
    Button btnThanhToan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //managementCart = new ManagementCart(this);
        setVariable();
        calculatePayment();
        controls();
        Event();


    }

    private void Event() {
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThanhToan();
            }
            private void ThanhToan() {
                Intent intent=new Intent(PaymentActivity.this,OderActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Xác nhận thanh toán thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void controls() {
        btnThanhToan=findViewById(R.id.btnThanhToan);
        txtTotal=findViewById(R.id.txtTotal);
    }


    private void calculatePayment() {
        SharedPreferences sharedPreferences = getSharedPreferences("ThanhToan", MODE_PRIVATE);
        Float total = sharedPreferences.getFloat("total", 1);
        DecimalFormat currencyFormat = new DecimalFormat("###,###,### VNĐ");
        binding.txtTotal.setText(currencyFormat.format(total));
        Intent intent = getIntent();
        if (intent != null ) {
            String totalAmountFormatted = intent.getStringExtra("total_amount");
            binding.txtTotal.setText(totalAmountFormatted);
        }
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