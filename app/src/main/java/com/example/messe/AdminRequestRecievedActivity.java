package com.example.messe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AdminRequestRecievedActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_request_recieved);
        Button workerButton = findViewById(R.id.adminRequestRecievedWorkerButton);
        Button studentButton = findViewById(R.id.adminRequestRecievedStudentButton);
        String date = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
        workerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workerButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_pink));
                studentButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_white));
                ArrayList<ItemSpecialRequest> worker=new ArrayList<>();
                replaceFragment(new AdminManageInventoryUtilitiesFragment());
                Log.w("Date", date);
                db.collection("special_request_worker").whereEqualTo("Date",date).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
//                                ItemSpecialRequest req= (ItemSpecialRequest) document.get(String.valueOf(ItemSpecialRequest.class));
//                                Log.w("done","done");
//                                if(document.get("status").toString()=="Null") worker.add(req);
                                String firstName=document.get("firstName").toString();
                                String lastName=document.get("lastName").toString();
                                String reqId=document.get("reqId").toString();
                                String reqMessage=document.get("reqMessage").toString();
                                String status=document.get("status").toString();
                                Log.w("first",firstName);
                            }
                        }
                    }
                });
            }
        });

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workerButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_white));
                studentButton.setBackground(getResources().getDrawable(R.drawable.layout_bottom_round_rect_pink));
                replaceFragment(new AdminManageInventoryStoredFragment());
                ArrayList<ItemSpecialRequest> student=new ArrayList<>();
                db.collection("special_request_student").whereEqualTo("Date",date).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
//                                ItemSpecialRequest req= (ItemSpecialRequest) document.get(String.valueOf(ItemSpecialRequest.class));
//                                Log.w("done","done");
//                                if(document.get("status").toString()=="Null") student.add(req);
                                String firstName=document.get("firstName").toString();
                                String lastName=document.get("lastName").toString();
                                String reqId=document.get("reqId").toString();
                                String reqMessage=document.get("reqMessage").toString();
                                String status=document.get("status").toString();
                                Log.w("first",firstName);
                            }
                        }
                    }
                });
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.adminRequestRecievedFragment, fragment);
        fragmentTransaction.commit();
    }
}