package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StudentDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        // get links to all the linear layouts
        LinearLayout feedback = (LinearLayout)findViewById(R.id.feedback);
        LinearLayout markAbsence = (LinearLayout)findViewById(R.id.markAbsence);
        LinearLayout specialRequest = (LinearLayout)findViewById(R.id.specialRequest);
        LinearLayout checkMenu = (LinearLayout)findViewById(R.id.checkMenu);

        // set the height of all the linear layout to be same as width
        markAbsence.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams mParams;
                mParams = (LinearLayout.LayoutParams) markAbsence.getLayoutParams();
                mParams.height = markAbsence.getWidth();
                markAbsence.setLayoutParams(mParams);
                markAbsence.postInvalidate();
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
        feedback.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams mParams;
                mParams = (LinearLayout.LayoutParams) feedback.getLayoutParams();
                mParams.height = feedback.getWidth();
                feedback.setLayoutParams(mParams);
                feedback.postInvalidate();
            }
        });

        // go to respective activity on clicking the options of student dashboard
        markAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboardActivity.this, StudentMarkAbsenceActivity.class));
            }
        });
        specialRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboardActivity.this, StudentSpecialRequestActivity.class));
            }
        });
        checkMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboardActivity.this, CheckMenuActivity.class));
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboardActivity.this, StudentFeedbackActivity.class));
            }
        });

        TextView idTV = findViewById(R.id.studentDashboardId);
        TextView nameTV = findViewById(R.id.studentDashboardName);

        String sid = "", fname = "", lname = "";
        // from data base store the first name and last and id in above variables

        // code end here

        idTV.setText("ID: " + sid);
        nameTV.setText("Name: " + fname + " " + lname);
    }

    public void logout(View view) {

    }
}