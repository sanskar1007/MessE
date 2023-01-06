package com.example.messe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class WorkerDashboardActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private long pressedTime;
    String sid = "", fname = "", lname = "";

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
                Intent i = new Intent(getApplicationContext(), WorkerSpecialRequestHelperActivity.class);
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
        // from data base store the first name and last and id in above variables

        db.collection("worker").whereEqualTo("id",id).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        fname=document.get("firstName").toString();
                        lname=document.get("lastName").toString();
                    }
                }
                idTV.setText("ID: " + id);
                nameTV.setText("Name: " + fname + " " + lname);
            }
        });
        idTV.setText("ID: " + id);
        nameTV.setText("Name: " + fname + " " + lname);
        // code end here
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