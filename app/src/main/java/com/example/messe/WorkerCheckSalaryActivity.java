package com.example.messe;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WorkerCheckSalaryActivity extends AppCompatActivity {
    // variables to link to the textviews whose data has to be changed
    TextView presentDays, absentDays, totalDays, fromDate, toDate, salary;
    // intent from parent activity
    String id;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_check_salary);

        Bundle extras = getIntent().getExtras();
        id = extras.getString("Value1");

        // link the views
        fromDate = findViewById(R.id.workerCheckSalaryDateFrom);
        toDate = findViewById(R.id.workerCheckSalaryDateTo);
        totalDays = findViewById(R.id.workerCheckSalaryWorkingDays);
        presentDays = findViewById(R.id.workerCheckSalaryPresence);
        absentDays = findViewById(R.id.workerCheckSalaryAbsent);
        salary = findViewById(R.id.workerCheckSalaryPayable);
        // set the data of the current worker in the respective fields
        setData();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setData() {
        // from database worker
        db.collection("worker").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        String mi = document.get("id").toString();
                        String a = document.get("attendance").toString();
                        String lad = document.get("lastAttendanceDate").toString();
                        String perDaySalary = document.get("perDaySalary").toString();

                        if(mi.equals(id)) {
                            // for the last attendance date
                            String lastMarkedAtten = lad;
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate date = LocalDate.parse(lastMarkedAtten, formatter);
                            // compute the first date of current month
                            LocalDate firstDateOfMonth = date.withDayOfMonth(1);
                            String firstDateOfMonthString = firstDateOfMonth.format(formatter);
                            // compute the days passed and total absent days
                            String daysPassed = "" + date.getDayOfMonth();
                            String absent = "" + (date.getDayOfMonth()-Integer.parseInt(a));

                            // set the data of the fields
                            fromDate.setText(firstDateOfMonthString);
                            toDate.setText(lad);
                            totalDays.setText(daysPassed);
                            presentDays.setText(a);
                            absentDays.setText(absent);
                            salary.setText("Rs. "+(Double.parseDouble(perDaySalary)*Double.parseDouble(a)));
                        }
                    }
                }
            }
        });
    }
}