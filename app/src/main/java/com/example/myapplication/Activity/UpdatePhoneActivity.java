package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityAddPhoneBinding;
import com.example.myapplication.databinding.ActivityUpdatePhoneBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdatePhoneActivity extends BaseActivity {
    ActivityUpdatePhoneBinding binding;

    String bestPhone, categoryId, description, id, imagePath, locationId,
            price, priceId, star, timeId, timeValue, title;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdatePhoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));

        setVariable();
    }

    private void setVariable(){

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnLuuChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bestPhone = binding.edtBestPhone.getText().toString();
                categoryId = binding.edtCategoryId.getText().toString();
                description = binding.edtDescription.getText().toString();
                id = binding.edtId.getText().toString();
                imagePath = binding.edtImagePath.getText().toString();
                locationId = binding.edtLocationId.getText().toString();
                price = binding.edtPrice.getText().toString();
                priceId = binding.edtPriceId.getText().toString();
                star = binding.edtStar.getText().toString();
                timeId = binding.edtTimeId.getText().toString();
                timeValue = binding.edtTimeValue.getText().toString();
                title = binding.edtTitle.getText().toString();

                if (bestPhone.isEmpty()||categoryId.isEmpty()||description.isEmpty()||id.isEmpty()||imagePath.isEmpty()
                        ||locationId.isEmpty()||price.isEmpty()||priceId.isEmpty()||star.isEmpty()||timeId.isEmpty()
                        ||timeValue.isEmpty()||title.isEmpty()) {

                    Toast.makeText(UpdatePhoneActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    updatePhoneToFirebase();
                }
            }
        });
    }
    private void updatePhoneToFirebase() {
        HashMap<String, Object> phoneHashMap = new HashMap<>();
        phoneHashMap.put("BestPhone", bestPhone);
        phoneHashMap.put("CategoryId", categoryId);
        phoneHashMap.put("Description", description);
        phoneHashMap.put("Id", id);
        phoneHashMap.put("ImagePath", imagePath);
        phoneHashMap.put("LocationId", locationId);
        phoneHashMap.put("Price", price);
        phoneHashMap.put("PriceId", priceId);
        phoneHashMap.put("Star", star);
        phoneHashMap.put("TimeId", timeId);
        phoneHashMap.put("TimeValue", timeValue);
        phoneHashMap.put("Title", title);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Phones");

        myRef.updateChildren(phoneHashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Xử lý khi cập nhật thành công
                        Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Xử lý khi cập nhật thất bại
                        Toast.makeText(getApplicationContext(), "Cập nhật thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}