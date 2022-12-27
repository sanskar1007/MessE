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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminManageInventoryUpdateActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
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
        // return the quantity of "type" from "name" category of manage_inventory
        DocumentReference docRef = db.collection("manage_inventory").document(type);
        final int[] quant = new int[1];
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        quant[0] = Integer.parseInt(document.getData().get(name).toString());
                        currentQuantity.setText(""+quant[0]);
                        Log.d("TAG", "DocumentSnapshot data: " + document.getData().get(name));
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });
        Log.d("TAG", "DocumentSnapshot data: " + quant[0]);
        return quant[0];
    }

    private String[] getInventoryName() {
        String sinventoryType = inventoryType.getSelectedItem().toString();
        ArrayList<String> list = new ArrayList<>();

        if(sinventoryType.equals("Utilities")==true){
            // add the inventory in "list" from utilities
            DocumentReference docRef = db.collection("manage_inventory").document("Utilities");
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            for (Map.Entry<String,Object> mapElement : document.getData().entrySet()) {
                                String key = mapElement.getKey();
                                list.add(key);
                                String sinventoryName[] =list.toArray(new String[0]);
                                ArrayAdapter<String> inventoryNameAdapter = new ArrayAdapter<>(AdminManageInventoryUpdateActivity.this,
                                        R.layout.background_spinner_pink, sinventoryName);
                                inventoryNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                inventoryName.setAdapter(inventoryNameAdapter);
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
//            list.add("Soap");
//            list.add("Glasses");
//            list.add("Plates");
        } else if(sinventoryType.equals("Stored")==true){
            // return the inventory in "list" from stored_food
            DocumentReference docRef = db.collection("manage_inventory").document("Stored");
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            for (Map.Entry<String,Object> mapElement : document.getData().entrySet()) {
                                String key = mapElement.getKey();
                                list.add(key);
                                String sinventoryName[] =list.toArray(new String[0]);
                                ArrayAdapter<String> inventoryNameAdapter = new ArrayAdapter<>(AdminManageInventoryUpdateActivity.this,
                                        R.layout.background_spinner_pink, sinventoryName);
                                inventoryNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                inventoryName.setAdapter(inventoryNameAdapter);
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
//            list.add("Onion");
//            list.add("Potato");
//            list.add("Tomato");
//            list.add("Capsicum");
        } else if(sinventoryType.equals("Daily")==true) {
            // return the inventory in "list" from frequently_bought_food
            DocumentReference docRef = db.collection("manage_inventory").document("Daily");
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            for (Map.Entry<String,Object> mapElement : document.getData().entrySet()) {
                                String key = mapElement.getKey();
                                list.add(key);
                                String sinventoryName[] =list.toArray(new String[0]);
                                ArrayAdapter<String> inventoryNameAdapter = new ArrayAdapter<>(AdminManageInventoryUpdateActivity.this,
                                        R.layout.background_spinner_pink, sinventoryName);
                                inventoryNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                inventoryName.setAdapter(inventoryNameAdapter);
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
//            list.add("Milk");
//            list.add("Eggs");
//            list.add("Biscuit");
//            list.add("Bread");
        }

        return list.toArray(new String[0]);
    }

    public void submit(View view) {
        String type = inventoryType.getSelectedItem().toString();
        String name = inventoryName.getSelectedItem().toString();
        int quan = Integer.parseInt(newQuantity.getText().toString());

        // update the database for the "type", name:quan
        Map<String,Object> request=new HashMap<>();
        request.put(name,quan);
        db.collection("manage_inventory").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot DocumentSnapshot=task.getResult().getDocuments().get(0);
                    String documentID= DocumentSnapshot.getId();
                    db.collection("manage_inventory").document(type).update(request);
                }
            }
        });
        currentQuantity.setText(newQuantity.getText().toString());
    }
}