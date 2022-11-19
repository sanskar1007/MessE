package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class FoodDemandActivity extends AppCompatActivity {

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
        // test values are given for now
        int foodDemandBreakfastCountRetrieved = 700;
        int foodDemandLunchCountRetrieved = 300;
        int foodDemandSnacksCountRetrieved = 600;
        int foodDemandDinnerCountRetrieved = 100;

        foodDemandBreakfastCount.setText(""+foodDemandBreakfastCountRetrieved);
        foodDemandLunchCount.setText(""+foodDemandLunchCountRetrieved);
        foodDemandSnacksCount.setText(""+foodDemandSnacksCountRetrieved);
        foodDemandDinnerCount.setText(""+foodDemandDinnerCountRetrieved);
    }
}