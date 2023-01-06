package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class StudentSpecialRequestHelperActivity extends AppCompatActivity {
    FirebaseAuth auth;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_special_request_helper);

    }

    public void pastRequest(View view) {
        startActivity(new Intent(StudentSpecialRequestHelperActivity.this, StudentSpecialRequestStatusActivity.class));
    }

    public void newRequest(View view) {
        startActivity(new Intent(StudentSpecialRequestHelperActivity.this, StudentSpecialRequestActivity.class));
    }
}