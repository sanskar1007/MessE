package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StudentLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                startActivity(new Intent(StudentLoginActivity.this, StudentDashboardActivity.class));
                finish();
            }
        }, 1000);
    }
}