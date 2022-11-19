package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class AdminLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                startActivity(new Intent(AdminLoginActivity.this, AdminDashboardActivity.class));
                finish();
            }
        }, 1000);
    }
}