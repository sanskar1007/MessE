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

import java.util.ArrayList;

public class WorkerSpecialRequestStatusActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<ItemSpecialRequest> arrayList = new ArrayList<>();
    AdapterRequestStatus adapter;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_special_request_status);

        Bundle extras = getIntent().getExtras();
        id = extras.getString("Value1");
        initData();
        recyclerView = findViewById(R.id.workerSpecialRequestStatusRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterRequestStatus(arrayList);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        db.collection("special_request_worker").whereEqualTo("reqId",id).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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