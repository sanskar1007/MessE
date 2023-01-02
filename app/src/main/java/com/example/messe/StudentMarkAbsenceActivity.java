package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentMarkAbsenceActivity extends AppCompatActivity {
    CheckBox breakfast, lunch, snacks, dinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mark_absence);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateArray[] = formatter.format(date).toString().split(" ");
        TextView adminSellCouponDate = findViewById(R.id.studentMarkAbsenceDate);
        adminSellCouponDate.setText("Date: " + dateArray[0]);

        breakfast = findViewById(R.id.studentMarkAbsenceBreakfastCheckbox);
        lunch = findViewById(R.id.studentMarkAbsenceLunchCheckbox);
        snacks = findViewById(R.id.studentMarkAbsenceSnacksCheckbox);
        dinner = findViewById(R.id.studentMarkAbsenceDinnerCheckbox);
    }

    public void submit(View view) {
        if(breakfast.isChecked()) {
            //reduce the breakfast by 1
        }
        if(lunch.isChecked()) {
            //reduce the lunch by 1
        }
        if(snacks.isChecked()) {
            //reduce the snacks by 1
        }
        if(dinner.isChecked()) {
            //reduce the dinner by 1
        }


    }
}