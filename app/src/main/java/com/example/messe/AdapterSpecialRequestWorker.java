package com.example.messe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterSpecialRequestWorker extends RecyclerView.Adapter<AdapterSpecialRequestWorker.SpecialRequestViewHolder> {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<ItemSpecialRequest> arrayList;
    public AdapterSpecialRequestWorker(ArrayList<ItemSpecialRequest> arrayList){
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public AdapterSpecialRequestWorker.SpecialRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special_request, parent, false);
        return new SpecialRequestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSpecialRequestWorker.SpecialRequestViewHolder holder, int position) {
        final String fName = arrayList.get(position).getFirstName();
        final String lName = arrayList.get(position).getLastName();
        final String rId = arrayList.get(position).getReqId();
        final String rMessage = arrayList.get(position).getReqMessage();
        final String stat = arrayList.get(position).getStatus();

        String heading = "" + fName + " " + lName + " (" + rId +")";
        String body = "" + rMessage;

        holder.setData(heading, body, stat);

        holder.srAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.get(holder.getAdapterPosition()).getStatus().equals("Null")==true){
                    arrayList.get(holder.getAdapterPosition()).setStatus("True");
                    holder.srAccept.setBackgroundResource(R.drawable.layout_bottom_left_round_rect_green);
                    holder.srDecline.setBackgroundResource(R.drawable.layout_bottom_right_round_rect_black);
                    // update status in the database
                    Map<String,Object> request=new HashMap<>();
                    request.put("status","True");
                    db.collection("special_request_worker").whereEqualTo("reqMessage",rMessage).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot DocumentSnapshot=task.getResult().getDocuments().get(0);
                                String documentID= DocumentSnapshot.getId();
                                db.collection("special_request_worker").document(documentID).update(request);
                            }
                        }
                    });
                }
            }
        });
        holder.srDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.get(holder.getAdapterPosition()).getStatus().equals("Null")==true){
                    arrayList.get(holder.getAdapterPosition()).setStatus("True");
                    holder.srDecline.setBackgroundResource(R.drawable.layout_bottom_right_round_rect_green);
                    holder.srAccept.setBackgroundResource(R.drawable.layout_bottom_left_round_rect_black);
                    // update status in the database
                    Map<String,Object> request=new HashMap<>();
                    request.put("status","False");
                    db.collection("special_request_worker").whereEqualTo("reqMessage",rMessage).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot DocumentSnapshot=task.getResult().getDocuments().get(0);
                                String documentID= DocumentSnapshot.getId();
                                db.collection("special_request_worker").document(documentID).update(request);
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class SpecialRequestViewHolder extends RecyclerView.ViewHolder{
        TextView srItemText, srItemId;
        Button srAccept, srDecline;
        public SpecialRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            srItemText = itemView.findViewById(R.id.specialRequestItemText);
            srItemId = itemView.findViewById(R.id.specialRequestItemId);
            srAccept = itemView.findViewById(R.id.specialRequestItemAccept);
            srDecline = itemView.findViewById(R.id.specialRequestItemDecline);
        }

        public void setData(String heading, String body, String stat) {
            srItemId.setText(heading);
            srItemText.setText(body);
            if(stat.equals("False")==true){
                srAccept.setBackgroundResource(R.drawable.layout_bottom_left_round_rect_black);
                srDecline.setBackgroundResource(R.drawable.layout_bottom_right_round_rect_green);
            } else if(stat.equals("True")==true){
                srAccept.setBackgroundResource(R.drawable.layout_bottom_left_round_rect_green);
                srDecline.setBackgroundResource(R.drawable.layout_bottom_right_round_rect_black);
            } else {
                srAccept.setBackgroundResource(R.drawable.layout_bottom_left_round_rect_white);
                srDecline.setBackgroundResource(R.drawable.layout_bottom_right_round_rect_white);
            }
        }
    }
}
