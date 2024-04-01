package com.example.myapplication.Activity;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.PhoneListAdapter;
import com.example.myapplication.Domain.Phones;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityPhoneListBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PhoneListActivity extends BaseActivity {
    ActivityPhoneListBinding binding;
    private RecyclerView.Adapter adapterPhoneList;
    private int categoryId;
    private String categoryName;
    private String searchText;
    private boolean isSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        initList();
        setVariable();
    }

    private void setVariable() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnCrud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneListActivity.this, ChooseCrudActivity.class);
                startActivity(intent);
            }
            });
    }

    private void initList() {
        DatabaseReference myRef = database.getReference("Phones");
        binding.progressBarPhoneList.setVisibility(View.VISIBLE);
        ArrayList<Phones> list = new ArrayList<>();

        Query query;
        if (isSearch) {
            query = myRef.orderByChild("Tittle").startAt(searchText).endAt(searchText + '\uf8ff');
        } else {
            query = myRef.orderByChild("CategoryId").equalTo(categoryId);
        }
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Phones.class));
                    }
                    if (list.size() > 0) {
                        binding.phoneListView.setLayoutManager(new GridLayoutManager(PhoneListActivity.this, 1));
                        adapterPhoneList = new PhoneListAdapter(list);
                        binding.phoneListView.setAdapter(adapterPhoneList);
                    }
                    binding.progressBarPhoneList.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryId", 0);
        categoryName = getIntent().getStringExtra("Category");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch", false);

        binding.txtTitle.setText(categoryName);
        binding.btnBack.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }));
    }
}