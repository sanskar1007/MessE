package com.example.messe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class CheckMenuActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView BreakfastText,LunchText,SnacksText,DinnerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_menu);
        Spinner workerCheckMenuSpinner = (Spinner) findViewById(R.id.checkMenuSpinner);
        ArrayAdapter<String> daysAdapter = new ArrayAdapter<>(CheckMenuActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.days));
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workerCheckMenuSpinner.setAdapter(daysAdapter);

        workerCheckMenuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String day=workerCheckMenuSpinner.getSelectedItem().toString();
                Log.w("TAG", day);
                BreakfastText=findViewById(R.id.checkMenuBreakfastText);
                LunchText=findViewById(R.id.checkMenuLunchText);
                SnacksText=findViewById(R.id.checkMenuSnacksText);
                DinnerText=findViewById(R.id.checkMenuDinnerText);
                db.collection("menu").whereEqualTo("Day",day).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                String Breakfast=document.get("Breakfast").toString();
                                String Lunch=document.get("Lunch").toString();
                                String Snacks=document.get("Snacks").toString();
                                String Dinner=document.get("Dinner").toString();
                                BreakfastText.setText(Breakfast);
                                LunchText.setText(Lunch);
                                SnacksText.setText(Snacks);
                                DinnerText.setText(Dinner);
                            }
                        }
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.w("TAG", "day");
            }
        });
    }
}