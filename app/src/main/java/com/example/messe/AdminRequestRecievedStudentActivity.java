package com.example.messe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AdminRequestRecievedStudentActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView recyclerViewWorker;
    LinearLayoutManager layoutManager;
    ArrayList<ItemSpecialRequest> arrayList = new ArrayList<>();
    AdapterSpecialRequestStudent adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_request_recieved_student);

        initData();
        recyclerViewWorker = findViewById(R.id.adminRequestRecievedStudentRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewWorker.setLayoutManager(layoutManager);
        adapter = new AdapterSpecialRequestStudent(arrayList);
        recyclerViewWorker.setAdapter(adapter);
    }
    private void initData() {
        String date = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
        db.collection("special_request_student").whereEqualTo("Date",date).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        String fn = document.get("firstName").toString();
                        String ln = document.get("lastName").toString();
                        String ri = document.get("reqId").toString();
                        String rm = document.get("reqMessage").toString();
                        String st = document.get("status").toString();
                        String d = document.get("Date").toString();
                        Log.e("", fn+ln+ri+rm+st);
                        ItemSpecialRequest temp = new ItemSpecialRequest(fn, ln, ri, rm, st, d);
                        arrayList.add(temp);
                        Log.v("", "" + arrayList.size() + " " + temp.getFirstName());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}