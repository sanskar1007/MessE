package com.example.messe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminManageInventoryUpdateActivity extends AppCompatActivity {
    Spinner inventoryType;
    Spinner inventoryName;
    TextView currentQuantity;
    TextView newQuantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_inventory_update);

        inventoryType = (Spinner) findViewById(R.id.adminManageInventoryUpdateTypeSpinner);
        inventoryName = (Spinner) findViewById(R.id.adminManageInventoryUpdateNameSpinner);
        currentQuantity = findViewById(R.id.adminManageInventoryUpdateQuantity);
        newQuantity = findViewById(R.id.adminManageInventoryUpdateNewQuantityEditText);


        ArrayAdapter<String> inventoryTypeAdapter = new ArrayAdapter<>(AdminManageInventoryUpdateActivity.this,
                R.layout.background_spinner_pink, getResources().getStringArray(R.array.inventory));
        inventoryTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inventoryType.setAdapter(inventoryTypeAdapter);

        inventoryType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                String sinventoryName[] = getInventoryName();
                ArrayAdapter<String> inventoryNameAdapter = new ArrayAdapter<>(AdminManageInventoryUpdateActivity.this,
                        R.layout.background_spinner_pink, sinventoryName);
                inventoryNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                inventoryName.setAdapter(inventoryNameAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        inventoryName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                String sInventoryType = inventoryType.getSelectedItem().toString();
                String sInventoryName = inventoryName.getSelectedItem().toString();
                int currQuan = getCurrQuantityOfInventory(sInventoryType, sInventoryName);
                currentQuantity.setText(""+currQuan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


    }

    private int getCurrQuantityOfInventory(String type, String name) {
        // return the quanitity of "type" from "name" category of manage_inventory
        return 7;
    }

    private String[] getInventoryName() {
        String sinventoryType = inventoryType.getSelectedItem().toString();
        ArrayList<String> list = new ArrayList<>();

        if(sinventoryType.equals("Utilities")==true){
            // add the inventory in "list" from utilities
            list.add("Soap");
            list.add("Glasses");
            list.add("Plates");
        } else if(sinventoryType.equals("Stored")==true){
            // return the inventory in "list" from stored_food
            list.add("Onion");
            list.add("Potato");
            list.add("Tomato");
            list.add("Capcicum");
        } else if(sinventoryType.equals("Daily")==true) {
            // return the inventory in "list" from frequently_bought_food
            list.add("Milk");
            list.add("Eggs");
            list.add("Biscuit");
            list.add("Bread");
        }

        return list.toArray(new String[0]);
    }

    public void submit(View view) {
        String type = inventoryType.getSelectedItem().toString();
        String name = inventoryName.getSelectedItem().toString();
        int quan = Integer.parseInt(newQuantity.getText().toString());

        // update the database for the "type", name:quan
    }
}