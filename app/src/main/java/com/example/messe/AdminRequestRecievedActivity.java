package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminRequestRecievedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_request_recieved);
        Button workerButton = findViewById(R.id.adminRequestRecievedWorkerButton);
        Button studentButton = findViewById(R.id.adminRequestRecievedStudentButton);

        workerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workerButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_pink));
                studentButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_white));
                replaceFragment(new AdminManageInventoryUtilitiesFragment());
            }
        });

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workerButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_white));
                studentButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_pink));
                replaceFragment(new AdminManageInventoryStoredFragment());
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.adminRequestRecievedFragment, fragment);
        fragmentTransaction.commit();
    }
}