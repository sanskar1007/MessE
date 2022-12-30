package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class AdminLoginActivity extends AppCompatActivity {
    TextView email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }

    public void login(View view) {
        email = findViewById(R.id.adminLoginEmailEditText);
        password = findViewById(R.id.adminLoginPasswordEditText);
        // please enter your code here and remove the below line
        startActivity(new Intent(AdminLoginActivity.this, AdminDashboardActivity.class));
    }
}