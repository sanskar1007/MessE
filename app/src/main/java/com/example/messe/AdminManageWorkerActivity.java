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

public class AdminManageWorkerActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView recyclerViewWorker;
    LinearLayoutManager layoutManager;
    ArrayList<ItemManageWorker> arrayList = new ArrayList<>();
    AdapterManageWorker adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_worker);

        initData();

//        arrayList.add(new ItemManageWorker("A", "A", "19ucs162", "3.5", "10", "Null"));
//        arrayList.add(new ItemManageWorker("B", "B", "19ucs163", "3.7", "15", "True"));
//        arrayList.add(new ItemManageWorker("C", "C", "19ucs164", "3.4", "20", "False"));
//        arrayList.add(new ItemManageWorker("D", "D", "19ucs165", "4.5", "50", "Null"));
//        arrayList.add(new ItemManageWorker("E", "E", "19ucs166", "1.5", "10", "Null"));

        recyclerViewWorker = findViewById(R.id.adminManageWorkerRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewWorker.setLayoutManager(layoutManager);
        adapter = new AdapterManageWorker(arrayList);
        recyclerViewWorker.setAdapter(adapter);
    }
    private void initData() {
        String date = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
        db.collection("worker").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        String fn = document.get("firstName").toString();
                        String ln = document.get("lastName").toString();
                        String mi = document.get("id").toString();
                        String oar = document.get("overallRating").toString();
                        String cr = document.get("countRating").toString();
                        String st = document.get("status").toString();

                        Log.e("", fn + " " + ln + " " + mi + " " + oar + " " + cr + " "+ st);

                        ItemManageWorker temp = new ItemManageWorker(fn, ln, mi, oar, cr, st);
                        arrayList.add(temp);
                        Log.v("", "" + arrayList.size() + " " + temp.getFirstName());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}