package com.example.messe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Duration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class AdminRequestRecievedWorkerActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView recyclerViewWorker;
    LinearLayoutManager layoutManager;
    ArrayList<ItemSpecialRequest> arrayList = new ArrayList<>();
    AdapterSpecialRequest adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_request_recieved_worker);

        initData();
        Log.e("abc", "out now");

//        arrayList.add(new ItemSpecialRequest("Pranjal", "Gupta", "1", "I want fried idli", "Null"));
//        arrayList.add(new ItemSpecialRequest("Sanskar", "Khandelwal", "2", "I want fried idli 2", "Null"));
//        arrayList.add(new ItemSpecialRequest("Rohit", "Gupta", "3", "I want fried idli 3", "Null"));
//        arrayList.add(new ItemSpecialRequest("Umang", "Goyal", "4", "I want fried idli 4", "Null"));
//        arrayList.add(new ItemSpecialRequest("Kartik", "Singhal", "5", "I want fried idli 5", "Null"));
//        Log.e("", "out now");
        recyclerViewWorker = findViewById(R.id.adminRequestRecievedWorkerRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewWorker.setLayoutManager(layoutManager);
        adapter = new AdapterSpecialRequest(arrayList);
        recyclerViewWorker.setAdapter(adapter);
    }
    private void initData() {
        String date = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
        db.collection("special_request_worker").whereEqualTo("Date",date).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        String fn = document.get("firstName").toString();
                        String ln = document.get("lastName").toString();
                        String ri = document.get("reqId").toString();
                        String rm = document.get("reqMessage").toString();
                        String st = document.get("status").toString();
                        Log.e("req", fn+ln+ri+rm+st);
                        ItemSpecialRequest temp = new ItemSpecialRequest(fn, ln, ri, rm, st);
                        arrayList.add(temp);
                        Log.v("", "" + arrayList.size() + " " + temp.getFirstName());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}