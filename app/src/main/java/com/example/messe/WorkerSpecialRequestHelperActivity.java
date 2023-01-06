package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class WorkerSpecialRequestHelperActivity extends AppCompatActivity {
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_special_request_helper);
        Bundle extras = getIntent().getExtras();
        id = extras.getString("Value1");
    }


    public void newRequest(View view) {
        Intent i = new Intent(getApplicationContext(), WorkerSpecialRequestActivity.class);
        i.putExtra("Value1", id);
        startActivity(i);
    }

    public void pastRequest(View view) {
        Intent i = new Intent(getApplicationContext(), WorkerSpecialRequestStatusActivity.class);
        i.putExtra("Value1", id);
        startActivity(i);
    }
}