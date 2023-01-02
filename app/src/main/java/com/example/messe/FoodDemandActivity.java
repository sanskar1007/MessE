package com.example.messe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodDemandActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    String dateArray[] = formatter.format(date).toString().split(" ");
    // test values are given for now
    int foodDemandBreakfastCountRetrieved = 700;
    int foodDemandLunchCountRetrieved = 700;
    int foodDemandSnacksCountRetrieved = 700;
    int foodDemandDinnerCountRetrieved = 700;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_demand);
        TextView foodDemandBreakfastCount = findViewById(R.id.foodDemandBreakfastCount);
        TextView foodDemandLunchCount = findViewById(R.id.foodDemandLunchCount);
        TextView foodDemandSnacksCount = findViewById(R.id.foodDemandSnacksCount);
        TextView foodDemandDinnerCount = findViewById(R.id.foodDemandDinnerCount);

        // store the value of retrieved count of demand in the following variables
        db.collection("food_demand").whereEqualTo("date",dateArray[0]).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        foodDemandBreakfastCountRetrieved=Integer.parseInt(document.get("breakfast").toString());
                        foodDemandLunchCountRetrieved =Integer.parseInt(document.get("lunch").toString());
                        foodDemandSnacksCountRetrieved=Integer.parseInt(document.get("snacks").toString());
                        foodDemandDinnerCountRetrieved=Integer.parseInt(document.get("dinner").toString());
                        foodDemandBreakfastCount.setText(""+foodDemandBreakfastCountRetrieved);
                        foodDemandLunchCount.setText(""+foodDemandLunchCountRetrieved);
                        foodDemandSnacksCount.setText(""+foodDemandSnacksCountRetrieved);
                        foodDemandDinnerCount.setText(""+foodDemandDinnerCountRetrieved);
                    }
                }
            }
        });

        foodDemandBreakfastCount.setText(""+foodDemandBreakfastCountRetrieved);
        foodDemandLunchCount.setText(""+foodDemandLunchCountRetrieved);
        foodDemandSnacksCount.setText(""+foodDemandSnacksCountRetrieved);
        foodDemandDinnerCount.setText(""+foodDemandDinnerCountRetrieved);
    }
}