package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityOderBinding;
import com.example.myapplication.databinding.ActivityPaymentBinding;

public class OderActivity extends BaseActivity {
ActivityOderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);
        binding = ActivityOderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setVariable();
    }
    private void setVariable() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OderActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}