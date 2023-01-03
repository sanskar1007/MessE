package com.example.messe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WorkerSpecialRequestActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView message;
    String firstName,lastName;
    boolean helper=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_special_request);
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("Value1");
        Button mButton=findViewById(R.id.workerSpecialRequestButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
                message=findViewById(R.id.workerSpecialRequestEditText);
                String messagefinal=message.getText().toString();
                String status="Null";
                db.collection("worker").whereEqualTo("id",id).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                 firstName=document.get("firstName").toString();
                                 lastName=document.get("lastName").toString();
                                 helper=true;
                            }
                        }
                    }
                });
                if(messagefinal.length()>0 && helper==true){
                    Map<String, Object> request = new HashMap<>();
                    request.put("firstName", firstName);
                    request.put("lastName", lastName);
                    request.put("reqId", id);
                    request.put("Date", date);
                    request.put("reqMessage", messagefinal);
                    request.put("status",status);
                    db.collection("special_request_worker")
                            .add(request)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("TAG", "Error adding document", e);
                                }
                            });
                    message.setText("");
                }
            }
        });
    }
}