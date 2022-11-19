package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // get links to all the linear layouts
        LinearLayout manageInventory = (LinearLayout)findViewById(R.id.manageInventory);
        LinearLayout manageWorker = (LinearLayout)findViewById(R.id.manageWorker);
        LinearLayout sellCoupon = (LinearLayout)findViewById(R.id.sellCoupon);
        LinearLayout checkMenu = (LinearLayout)findViewById(R.id.checkMenu);
        LinearLayout requestRecieved = (LinearLayout)findViewById(R.id.requestRecieved);
        LinearLayout foodDemand = (LinearLayout)findViewById(R.id.foodDemand);
        // set the height of all the linear layout to be same as width
        manageInventory.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams mParams;
                mParams = (LinearLayout.LayoutParams) manageInventory.getLayoutParams();
                mParams.height = manageInventory.getWidth();
                manageInventory.setLayoutParams(mParams);
                manageInventory.postInvalidate();
            }
        });
        manageWorker.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams mParams;
                mParams = (LinearLayout.LayoutParams) manageWorker.getLayoutParams();
                mParams.height = manageWorker.getWidth();
                manageWorker.setLayoutParams(mParams);
                manageWorker.postInvalidate();
            }
        });
        sellCoupon.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams mParams;
                mParams = (LinearLayout.LayoutParams) sellCoupon.getLayoutParams();
                mParams.height = sellCoupon.getWidth();
                sellCoupon.setLayoutParams(mParams);
                sellCoupon.postInvalidate();
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
        requestRecieved.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams mParams;
                mParams = (LinearLayout.LayoutParams) requestRecieved.getLayoutParams();
                mParams.height = requestRecieved.getWidth();
                requestRecieved.setLayoutParams(mParams);
                requestRecieved.postInvalidate();
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
        manageInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this, AdminManageInventoryActivity.class));
            }
        });
        manageWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this, AdminManageWorkerActivity.class));
            }
        });
        sellCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this, AdminSellCouponActivity.class));
            }
        });
        checkMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this, CheckMenuActivity.class));
            }
        });
        requestRecieved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this, AdminRequestRecievedActivity.class));
            }
        });
        foodDemand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this, FoodDemandActivity.class));
            }
        });
    }
}