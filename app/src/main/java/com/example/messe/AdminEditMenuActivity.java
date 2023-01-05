package com.example.messe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class AdminEditMenuActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Spinner adminEditMenuSpinner;
    EditText BreakfastText, LunchText, SnacksText, DinnerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_menu);

        adminEditMenuSpinner = (Spinner) findViewById(R.id.editMenuSpinner);
        ArrayAdapter<String> daysAdapter = new ArrayAdapter<>(AdminEditMenuActivity.this,
                R.layout.background_spinner_white, getResources().getStringArray(R.array.days));
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adminEditMenuSpinner.setAdapter(daysAdapter);

        adminEditMenuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String day=adminEditMenuSpinner.getSelectedItem().toString();
                Log.w("TAG", day);
                BreakfastText = findViewById(R.id.editMenuBreakfastText);
                LunchText = findViewById(R.id.editMenuLunchText);
                SnacksText = findViewById(R.id.editMenuSnacksText);
                DinnerText = findViewById(R.id.editMenuDinnerText);
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

    public void updateMenu(View view) {
        Map<String, Object> request = new HashMap<>();
        request.put("Day", adminEditMenuSpinner.getSelectedItem().toString());
        request.put("Breakfast", BreakfastText.getText().toString());
        request.put("Lunch", LunchText.getText().toString());
        request.put("Snacks", SnacksText.getText().toString());
        request.put("Dinner", DinnerText.getText().toString());
        db.collection("menu").document(adminEditMenuSpinner.getSelectedItem().toString()).set(request);
        Toast.makeText(this, "Menu Updated!", Toast.LENGTH_SHORT).show();
    }
}