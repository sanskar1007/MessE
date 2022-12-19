package com.example.messe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminSellCouponActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sell_coupon);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateArray[] = formatter.format(date).toString().split(" ");
        TextView adminSellCouponDate = findViewById(R.id.adminSellCouponDate);
        adminSellCouponDate.setText("Date: " + dateArray[0]);
        Spinner adminSellCouponSpinner = (Spinner) findViewById(R.id.adminSellCouponSpinner);

        ArrayAdapter<String> mealsAdapter = new ArrayAdapter<>(AdminSellCouponActivity.this,
                R.layout.background_spinner_pink, getResources().getStringArray(R.array.meals));

        mealsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adminSellCouponSpinner.setAdapter(mealsAdapter);
    }

    public void increaseCoupon(View view){
        TextView currentCount = findViewById(R.id.adminSellCouponCount);
        int currentCountInteger = Integer.parseInt(currentCount.getText().toString());
        currentCountInteger++;
        // boundary condition
        if(currentCountInteger>10)
            currentCountInteger=10;
        currentCount.setText(""+currentCountInteger);
    }

    public void decreaseCoupon(View view){
        TextView currentCount = findViewById(R.id.adminSellCouponCount);
        int currentCountInteger = Integer.parseInt(currentCount.getText().toString());
        currentCountInteger--;
        // boundary condition
        if(currentCountInteger<1)
            currentCountInteger=1;
        currentCount.setText(""+currentCountInteger);
    }

    public void submit(View view) {
        TextView adminSellCouponNameEditText = findViewById(R.id.adminSellCouponNameEditText);
        TextView adminSellCouponReasonEditText = findViewById(R.id.adminSellCouponReasonEditText);
        Spinner adminSellCouponSpinner = findViewById(R.id.adminSellCouponSpinner);
        TextView adminSellCouponCount = findViewById(R.id.adminSellCouponCount);

        String name = adminSellCouponNameEditText.getText().toString();
        String reason = adminSellCouponReasonEditText.getText().toString();
        String meal = adminSellCouponSpinner.getSelectedItem().toString();
        int count = Integer.parseInt(adminSellCouponCount.getText().toString());

        if (name.equals("")==true || reason.equals("")==true || meal.equals("")==true){
            Toast.makeText(this, "Fields can not be null", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(this, "Request Successfull", Toast.LENGTH_SHORT).show();
            adminSellCouponNameEditText.setText("");
            adminSellCouponReasonEditText.setText("");
            adminSellCouponCount.setText("1");
            // please add to the database the current request with
            // name, reason, count, meal
        }
    }
}