package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WorkerDashboardActivity extends AppCompatActivity {
    private long pressedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_dashboard);
        // get links to all the linear layouts
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("Value1");
        LinearLayout checkMenu = (LinearLayout)findViewById(R.id.checkMenu);
        LinearLayout specialRequest = (LinearLayout)findViewById(R.id.specialRequest);
        LinearLayout foodDemand = (LinearLayout)findViewById(R.id.foodDemand);

        // set the height of all the linear layout to be same as width


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

        checkMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkerDashboardActivity.this, CheckMenuActivity.class));
            }
        });
        specialRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), WorkerSpecialRequestActivity.class);
                i.putExtra("Value1", id);
                startActivity(i);
            }
        });
        foodDemand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkerDashboardActivity.this, FoodDemandActivity.class));
            }
        });

        TextView idTV = findViewById(R.id.workerDashboardId);
        TextView nameTV = findViewById(R.id.workerDashboardName);

        String sid = "", fname = "", lname = "";
        // from data base store the first name and last and id in above variables

        // code end here

        idTV.setText("ID: " + sid);
        nameTV.setText("Name: " + fname + " " + lname);


    }
    @Override
    public void onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
            moveTaskToBack(true);
            System.exit(0);
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

    public void logout(View view) {

    }
}