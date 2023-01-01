package com.example.messe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class StudentSpecialRequestActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_special_request);
        FirebaseAuth auth=FirebaseAuth.getInstance();
        Button mButton=findViewById(R.id.studentSpecialRequestButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=auth.getCurrentUser().getEmail();
                String name=auth.getCurrentUser().getDisplayName();
                String date = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
                message=findViewById(R.id.studentSpecialRequestEditText);
                String messagefinal=message.getText().toString();
                String[] arrSplit = name.split("\\s");
                String firstName=arrSplit[0];
                String lastName=arrSplit[1];
                String status="Null";
                if(messagefinal.length()>0){
                    Map<String, Object> request = new HashMap<>();
                    request.put("firstName", firstName);
                    request.put("lastName", lastName);
                    request.put("reqId", email);
                    request.put("Date", date);
                    request.put("reqMessage", messagefinal);
                    request.put("status",status);
                    db.collection("special_request_student")
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