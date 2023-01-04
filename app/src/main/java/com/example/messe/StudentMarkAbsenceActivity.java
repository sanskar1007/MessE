package com.example.messe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import android.content.SharedPreferences;
import android.widget.Toast;

public class StudentMarkAbsenceActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CheckBox breakfast, lunch, snacks, dinner;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    String dateArray[] = formatter.format(date).toString().split(" ");
    int breakfastdb, lunchdb, snacksdb, dinnerdb;
    boolean helper = true;
    private static final String PREFS_NAME = "MyPrefsFile2";
    private static final String dateshared = "date";
    Map<String, Object> demand = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mark_absence);
        TextView adminSellCouponDate = findViewById(R.id.studentMarkAbsenceDate);
        adminSellCouponDate.setText("Date: " + dateArray[0]);

        breakfast = findViewById(R.id.studentMarkAbsenceBreakfastCheckbox);
        lunch = findViewById(R.id.studentMarkAbsenceLunchCheckbox);
        snacks = findViewById(R.id.studentMarkAbsenceSnacksCheckbox);
        dinner = findViewById(R.id.studentMarkAbsenceDinnerCheckbox);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String lastshareddate = settings.getString(dateshared, "");
        Log.d("t",lastshareddate);
        if (lastshareddate.equals(dateArray[0])) {
            Button myButton = findViewById(R.id.workerSpecialRequestButton);
            myButton.setEnabled(false);
            myButton.setClickable(false);
        }
    }

    public void submit(View view) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(dateshared, dateArray[0]);
        editor.apply();
        db.collection("food_demand").whereEqualTo("date", dateArray[0]).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        breakfastdb = Integer.parseInt(document.get("breakfast").toString());
                        lunchdb = Integer.parseInt(document.get("lunch").toString());
                        snacksdb = Integer.parseInt(document.get("snacks").toString());
                        dinnerdb = Integer.parseInt(document.get("dinner").toString());
                        if (breakfast.isChecked()) {
                            breakfastdb--;
                            demand.put("breakfast", breakfastdb);
                            //reduce the breakfast by 1
                        }
                        if (lunch.isChecked()) {
                            lunchdb--;
                            demand.put("lunch", lunchdb);
                            //reduce the lunch by 1
                        }
                        if (snacks.isChecked()) {
                            snacksdb--;
                            demand.put("snacks", snacksdb);
                            //reduce the snacks by 1
                        }
                        if (dinner.isChecked()) {
                            dinnerdb--;
                            demand.put("dinner", dinnerdb);
                            //reduce the dinner by 1
                        }
                        DocumentSnapshot DocumentSnapshot = task.getResult().getDocuments().get(0);
                        String documentID = DocumentSnapshot.getId();
                        db.collection("food_demand").document(documentID).update(demand);
                        helper = false;
                    }
                }
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                testToast();
            }
        }, 2000);
        finish();
        startActivity(getIntent());
    }

    private void testToast() {
        if (helper == true) {
            breakfastdb=700;
            lunchdb=700;
            snacksdb=700;
            dinnerdb=700;
            Log.d("TAG", "Error adding document");
            if (breakfast.isChecked()) {
                breakfastdb--;
                //reduce the breakfast by 1
            }
            if (lunch.isChecked()) {
                lunchdb--;
                //reduce the lunch by 1
            }
            if (snacks.isChecked()) {
                snacksdb--;
                //reduce the snacks by 1
            }
            if (dinner.isChecked()) {
                dinnerdb--;
                //reduce the dinner by 1
            }
            demand.put("breakfast", breakfastdb);
            demand.put("lunch", lunchdb);
            demand.put("snacks", snacksdb);
            demand.put("dinner", dinnerdb);
            demand.put("date", dateArray[0]);
            DocumentReference food_demand = db.collection("food_demand").document();
            food_demand.set(demand);
        }
    }
}