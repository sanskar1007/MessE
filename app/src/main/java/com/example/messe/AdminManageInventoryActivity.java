package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminManageInventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_inventory);

        Button utilitiesButton = findViewById(R.id.adminManageInventoryUtilitiesButton);
        Button storedButton = findViewById(R.id.adminManageInventoryStoredButton);
        Button dailyButton = findViewById(R.id.adminManageInventoryDailyButton);

        utilitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utilitiesButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_pink));
                storedButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_white));
                dailyButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_white));
                replaceFragment(new AdminManageInventoryUtilitiesFragment());
            }
        });

        storedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utilitiesButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_white));
                storedButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_pink));
                dailyButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_white));
                replaceFragment(new AdminManageInventoryStoredFragment());
            }
        });

        dailyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utilitiesButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_white));
                storedButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_white));
                dailyButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_pink));
                replaceFragment(new AdminManageInventoryDailyFragment());
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.adminManageInventoryFragment, fragment);
        fragmentTransaction.commit();
    }
}