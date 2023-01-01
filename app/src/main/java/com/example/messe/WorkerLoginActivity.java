package com.example.messe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class WorkerLoginActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_login);
    }
    boolean helper;
    private void testToast() {
        if(helper==false){
            Toast.makeText(this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();}
    }
    public void login(View view) {
        helper=false;
        email = findViewById(R.id.workerLoginEmailEditText);
        password = findViewById(R.id.workerLoginPasswordEditText);
        // please enter your code here and remove the below line
        String id=email.getText().toString();
        String pass=password.getText().toString();
        Log.w("TAG", id);
        db.collection("worker_login").whereEqualTo("Id",id).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        Log.w("TAG", document.get("Password").toString());
                        Log.w("TAG", pass);
                        if(Integer.parseInt(pass)==Integer.parseInt(document.get("Password").toString())){
                            Log.w("TAG", pass);
                            startActivity(new Intent(WorkerLoginActivity.this, WorkerDashboardActivity.class));
                            helper=true;
                        }
                    }
                }
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                testToast();
            }
        }, 1000);
    }
}