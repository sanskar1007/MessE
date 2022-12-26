package com.example.messe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AdminManageInventoryAddActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_inventory_add);

        Spinner spinner = (Spinner) findViewById(R.id.adminManageInventoryAddSpinner);

        ArrayAdapter<String> inventoryAdapter = new ArrayAdapter<>(AdminManageInventoryAddActivity.this,
                R.layout.background_spinner_pink, getResources().getStringArray(R.array.inventory));

        inventoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(inventoryAdapter);
    }
    public void submit(View view) {
        TextView quantity = findViewById(R.id.adminManageInventoryAddQuantityEditText);
        TextView name = findViewById(R.id.adminManageInventoryAddNameEditText);
        Spinner spinner = findViewById(R.id.adminManageInventoryAddSpinner);

        String squantity = quantity.getText().toString();
        String sname = name.getText().toString();
        String sinventory = spinner.getSelectedItem().toString();

        if(sname.equals("")==true || sinventory.equals("")==true){
            Toast.makeText(this, "Fields can not be null", Toast.LENGTH_SHORT).show();
            return;
        } else if(Integer.parseInt(squantity)==0){
            Toast.makeText(this, "Quantity can not be 0", Toast.LENGTH_SHORT).show();
            return;
        } else {

            if(sinventory.equals("Utilities")==true) {
                // update the manage_inventory_ -> utilities to have sname: squantity
            } else if(sinventory.equals("Stored")==true) {
                // update the manage_inventory_ -> stored_food to have sname: squantity
            } else if(sinventory.equals("Daily")==true) {
                // update the manage_inventory_ -> frequently_bought_food to have sname: squantity
            }

            name.setText("");
            quantity.setText("");
            Toast.makeText(this, "Request Successful", Toast.LENGTH_SHORT).show();
        }

    }
}