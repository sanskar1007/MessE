package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class WorkerLoginActivity extends AppCompatActivity {
    TextView email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_login);
    }

    public void login(View view) {
        email = findViewById(R.id.workerLoginEmailEditText);
        password = findViewById(R.id.workerLoginPasswordEditText);
        // please enter your code here and remove the below line
        startActivity(new Intent(WorkerLoginActivity.this, WorkerDashboardActivity.class));
    }
}