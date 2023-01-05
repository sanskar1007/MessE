package com.example.messe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AdminDashboardActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "MyPrefsFile1";
    private static final String USERNAME_KEY = "username";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private long pressedTime;
    String sid = "", fname = "", lname = "";
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
        LinearLayout editMenu = (LinearLayout)findViewById(R.id.editMenu);
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
        editMenu.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams mParams;
                mParams = (LinearLayout.LayoutParams) editMenu.getLayoutParams();
                mParams.height = editMenu.getWidth();
                editMenu.setLayoutParams(mParams);
                editMenu.postInvalidate();
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
        editMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this, AdminEditMenuActivity.class));
            }
        });
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        sid = settings.getString(USERNAME_KEY, "");
        TextView idTV = findViewById(R.id.adminDashboardId);
        TextView nameTV = findViewById(R.id.adminDashboardName);
        db.collection("admin_login").whereEqualTo("Id",sid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        fname=document.get("firstName").toString();
                        lname=document.get("lastName").toString();
                    }
                }
                idTV.setText("ID: " + sid);
                nameTV.setText("Name: " + fname + " " + lname);
            }
        });
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
        finish();
    }
}