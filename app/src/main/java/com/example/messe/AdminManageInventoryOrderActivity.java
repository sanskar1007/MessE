package com.example.messe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class AdminManageInventoryOrderActivity extends AppCompatActivity {
    RecyclerView recyclerViewInventory;
    LinearLayoutManager layoutManager;
    ArrayList<ItemInventory> arrayList = new ArrayList<>();
    AdapterInventory adapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_inventory_order);

        Spinner adminManageInventoryOrderSpinner = (Spinner) findViewById(R.id.manageInventoryOrderSpinner);
        ArrayAdapter<String> inventoryType = new ArrayAdapter<>(AdminManageInventoryOrderActivity.this,
                R.layout.background_spinner_white, getResources().getStringArray(R.array.inventory));
        inventoryType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adminManageInventoryOrderSpinner.setAdapter(inventoryType);

        recyclerViewInventory = findViewById(R.id.manageInventoryOrderRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewInventory.setLayoutManager(layoutManager);


        adminManageInventoryOrderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(adminManageInventoryOrderSpinner.getSelectedItem().toString().equals("Utilities")){
                    arrayList = getUtilitiesInventory();
                    adapter = new AdapterInventory(arrayList);
                    recyclerViewInventory.setAdapter(adapter);
                } else if(adminManageInventoryOrderSpinner.getSelectedItem().toString().equals("Stored")){
                    arrayList = getStoredInventory();
                    adapter = new AdapterInventory(arrayList);
                    recyclerViewInventory.setAdapter(adapter);
                } else if(adminManageInventoryOrderSpinner.getSelectedItem().toString().equals("Daily")){
                    arrayList = getDailyInventory();
                    adapter = new AdapterInventory(arrayList);
                    recyclerViewInventory.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


    }

    public void submit(View view) {
        ArrayList<ItemInventory> newInventory = adapter.getArrayList();
        String order = "Name\t\t\tQuantity\n";

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateArray[] = formatter.format(date).toString().split(" ");

        order = "Date: " + dateArray[0] + " " + dateArray[1] + "\n" + order;

        for(ItemInventory item: newInventory){
            if(!item.getCount().equals("0")){
                order += item.getName() + "\t\t\t" + item.getCount() + "\n";
            } else {
                continue;
            }
        }
        // Create the text message with a string.
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, order);
        sendIntent.setType("text/plain");
        try {
            startActivity(sendIntent);
        } catch (ActivityNotFoundException e) {
            // Define what your app should do if no activity can handle the intent.
        }
    }

    private ArrayList<ItemInventory> getDailyInventory() {
        ArrayList<ItemInventory> list = new ArrayList<>();
        DocumentReference docRef = db.collection("manage_inventory").document("Daily");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        for (Map.Entry<String,Object> mapElement : document.getData().entrySet()) {
                            String key = mapElement.getKey();
                            String quan = "" + mapElement.getValue();
                            list.add(new ItemInventory(key, quan, "Utilities"));
                            adapter = new AdapterInventory(arrayList);
                            recyclerViewInventory.setAdapter(adapter);
                        }
                        Log.d("TAG", "DocumentSnapshot data: " + document.getData()+ list.get(1));
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });
        return list;
    }

    private ArrayList<ItemInventory> getStoredInventory() {
        ArrayList<ItemInventory> list = new ArrayList<>();
        DocumentReference docRef = db.collection("manage_inventory").document("Stored");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        for (Map.Entry<String,Object> mapElement : document.getData().entrySet()) {
                            String key = mapElement.getKey();
                            String quan = "" + mapElement.getValue();
                            list.add(new ItemInventory(key, quan, "Utilities"));
                            adapter = new AdapterInventory(arrayList);
                            recyclerViewInventory.setAdapter(adapter);
                        }
                        Log.d("TAG", "DocumentSnapshot data: " + document.getData()+ list.get(1));
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });
        return list;
    }

    private ArrayList<ItemInventory> getUtilitiesInventory() {
        ArrayList<ItemInventory> list = new ArrayList<>();
        DocumentReference docRef = db.collection("manage_inventory").document("Utilities");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        for (Map.Entry<String,Object> mapElement : document.getData().entrySet()) {
                            String key = mapElement.getKey();
                            String quan = "" + mapElement.getValue();
                            list.add(new ItemInventory(key, quan, "Utilities"));
                            adapter = new AdapterInventory(arrayList);
                            recyclerViewInventory.setAdapter(adapter);
                        }
                        Log.d("TAG", "DocumentSnapshot data: " + document.getData()+ list.get(1));
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });
        return list;
    }


}