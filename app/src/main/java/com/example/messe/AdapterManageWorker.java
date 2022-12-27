package com.example.messe;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
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

public class AdapterManageWorker extends RecyclerView.Adapter<AdapterManageWorker.ManageWorkerViewHolder> {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<ItemManageWorker> arrayList;
    public AdapterManageWorker(ArrayList<ItemManageWorker> arrayList){
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public AdapterManageWorker.ManageWorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manage_worker, parent, false);
        return new AdapterManageWorker.ManageWorkerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterManageWorker.ManageWorkerViewHolder holder, int position) {
        String fName = arrayList.get(position).getFirstName();
        String lName = arrayList.get(position).getLastName();
        String id = arrayList.get(position).getId();
        String stat = arrayList.get(position).getStatus();
        String overallRate = arrayList.get(position).getOverallRating();
        String countRate = arrayList.get(position).getCountRating();

        String heading = "" + fName + " " + lName + " (" + id +")";

        holder.setData(heading, overallRate, stat, countRate);

        holder.mwPresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.get(holder.getAdapterPosition()).getStatus().equals("Null")==true){
                    arrayList.get(holder.getAdapterPosition()).setStatus("True");
                    holder.mwPresent.setBackgroundResource(R.drawable.layout_bottom_left_round_rect_green);
                    holder.mwAbsent.setBackgroundResource(R.drawable.layout_bottom_right_round_rect_black);
                    double newOverallRating = calculateRating(Integer.parseInt(countRate), Double.parseDouble(overallRate), holder.mwRating.getRating());
                    // update overallrating , count+1 in the database
                    int count=Integer.parseInt(countRate);
                    count++;
                    String newCount=String.valueOf(count);
                    Map<String,Object> request=new HashMap<>();
                    request.put("countRating",newCount);
                    request.put("overallRating",String.valueOf(newOverallRating));
                    db.collection("worker").whereEqualTo("id",id).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot DocumentSnapshot=task.getResult().getDocuments().get(0);
                                String documentID= DocumentSnapshot.getId();
                                db.collection("worker").document(documentID).update(request);
                            }
                        }
                    });
                }
            }
        });
        holder.mwAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.get(holder.getAdapterPosition()).getStatus().equals("Null")==true){
                    arrayList.get(holder.getAdapterPosition()).setStatus("True");
                    holder.mwAbsent.setBackgroundResource(R.drawable.layout_bottom_right_round_rect_green);
                    holder.mwPresent.setBackgroundResource(R.drawable.layout_bottom_left_round_rect_black);
                    double newOverallRating = calculateRating(Integer.parseInt(countRate), Double.parseDouble(overallRate), holder.mwRating.getRating());
                    // update overallrating , count+1 in the database
                }
            }
        });

    }

    public double calculateRating(int countRate, double overallRate , float currRate){
        if(currRate==(float)0)
            return overallRate;
        else
            return (double)(overallRate*countRate+currRate)/(countRate+1);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ManageWorkerViewHolder extends RecyclerView.ViewHolder{
        TextView mwItemId;
        RatingBar mwRating;
        Button mwPresent, mwAbsent;
        public ManageWorkerViewHolder(@NonNull View itemView) {
            super(itemView);
            mwItemId = itemView.findViewById(R.id.manageWorkerItemId);
            mwPresent = itemView.findViewById(R.id.manageWorkerItemPresent);
            mwAbsent = itemView.findViewById(R.id.manageWorkerItemAbsent);
            mwRating = itemView.findViewById(R.id.manageWorkerRating);
        }

        public void setData(String heading, String rate, String stat, String count) {
            mwItemId.setText(heading);

            if(stat.equals("True")==true){
                mwPresent.setBackgroundResource(R.drawable.layout_bottom_left_round_rect_black);
                mwAbsent.setBackgroundResource(R.drawable.layout_bottom_right_round_rect_green);
            } else if(stat.equals("False")==true){
                mwPresent.setBackgroundResource(R.drawable.layout_bottom_left_round_rect_green);
                mwAbsent.setBackgroundResource(R.drawable.layout_bottom_right_round_rect_black);
            } else {
                mwPresent.setBackgroundResource(R.drawable.layout_bottom_left_round_rect_white);
                mwAbsent.setBackgroundResource(R.drawable.layout_bottom_right_round_rect_white);
            }
        }
    }
}