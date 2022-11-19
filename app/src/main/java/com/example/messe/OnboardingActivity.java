package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // create and link button variables to the button on the activity
        Button adminLoginButton = (Button)findViewById(R.id.adminLoginButton);
        Button studentLoginButton = (Button)findViewById(R.id.studentLoginButton);
        Button workerLoginButton = (Button)findViewById(R.id.workerLoginButton);

        // click ADMIN button to go to ADMIN login page
        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnboardingActivity.this, AdminLoginActivity.class));
            }
        });
        // click STUDENT button to go to ADMIN login page
        studentLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnboardingActivity.this, StudentLoginActivity.class));
            }
        });
        // click Worker button to go to ADMIN login page
        workerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnboardingActivity.this, WorkerLoginActivity.class));
            }
        });
    }
}