package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminManageInventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_inventory);

        Button orderButton = findViewById(R.id.adminManageInventoryOrderButton);
        Button updateButton = findViewById(R.id.adminManageInventoryUpdateButton);
        Button addButton = findViewById(R.id.adminManageInventoryAddButton);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminManageInventoryActivity.this, AdminManageInventoryUtilitiesActivity.class));
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminManageInventoryActivity.this, AdminManageInventoryStoredActivity.class));
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminManageInventoryActivity.this, AdminManageInventoryAddActivity.class));
            }
        });
    }
}