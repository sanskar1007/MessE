package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class WorkerDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_dashboard);
        // get links to all the linear layouts
        LinearLayout checkSalary = (LinearLayout)findViewById(R.id.checkSalary);
        LinearLayout checkMenu = (LinearLayout)findViewById(R.id.checkMenu);
        LinearLayout specialRequest = (LinearLayout)findViewById(R.id.specialRequest);
        LinearLayout foodDemand = (LinearLayout)findViewById(R.id.foodDemand);

        // set the height of all the linear layout to be same as width
        checkSalary.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams mParams;
                mParams = (LinearLayout.LayoutParams) checkSalary.getLayoutParams();
                mParams.height = checkSalary.getWidth();
                checkSalary.setLayoutParams(mParams);
                checkSalary.postInvalidate();
            }
        });

        checkMenu.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams mParams;
                mParams = (LinearLayout.LayoutParams) checkMenu.getLayoutParams();
                mParams.height = checkMenu.getWidth();
                checkMenu.setLayoutParams(mParams);
                checkMenu.postInvalidate();
            }
        });
        specialRequest.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams mParams;
                mParams = (LinearLayout.LayoutParams) specialRequest.getLayoutParams();
                mParams.height = specialRequest.getWidth();
                specialRequest.setLayoutParams(mParams);
                specialRequest.postInvalidate();
            }
        });
        foodDemand.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams mParams;
                mParams = (LinearLayout.LayoutParams) foodDemand.getLayoutParams();
                mParams.height = foodDemand.getWidth();
                foodDemand.setLayoutParams(mParams);
                foodDemand.postInvalidate();
            }
        });

        // go to respective activity on clicking the options of student dashboard
        checkSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkerDashboardActivity.this, WorkerCheckSalaryActivity.class));
            }
        });
        checkMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkerDashboardActivity.this, CheckMenuActivity.class));
            }
        });
        specialRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkerDashboardActivity.this, WorkerSpecialRequestActivity.class));
            }
        });
        foodDemand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkerDashboardActivity.this, FoodDemandActivity.class));
            }
        });
    }
}