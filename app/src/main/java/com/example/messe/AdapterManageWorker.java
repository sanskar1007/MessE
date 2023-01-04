package com.example.messe;

import android.graphics.Color;
import android.util.Log;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public String roundTo2Decimal(String s){
        if(s.length()<=3) return s;
        else return s.substring(0,3);
    }
    public String getTodayDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateArray[] = formatter.format(date).toString().split(" ");
        String today = dateArray[0];
        return today;
    }
    public boolean isAttendanceMarked(String lastAttendanceDate){
        return lastAttendanceDate.equals(getTodayDate());
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterManageWorker.ManageWorkerViewHolder holder, int position) {
        String fName = arrayList.get(position).getFirstName();
        String lName = arrayList.get(position).getLastName();
        String id = arrayList.get(position).getId();
        String stat = arrayList.get(position).getStatus();
        String overallRate = roundTo2Decimal(arrayList.get(position).getOverallRating());
        String atten = arrayList.get(position).getAttendance();
        String lastAttenDate = arrayList.get(position).getLastAttendanceDate();
        String[] arrOfStr = lastAttenDate.split("/", 2);
        String countRate = arrayList.get(position).getCountRating();
        Log.d("Tag",arrOfStr[0]);
        if(arrOfStr[0]=="01"){
            atten="0";
        }

        String heading = "" + fName + " " + lName + " (" + id +")";

        // if attendance is marked for today
        if(isAttendanceMarked(lastAttenDate)){
            holder.setData(heading, overallRate, atten, stat);
        } else {
            arrayList.get(holder.getAdapterPosition()).setStatus("Null");
            holder.setData(heading, overallRate, atten, "Null");
        }

        String finalAtten = atten;
        holder.mwPresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.get(holder.getAdapterPosition()).getStatus().equals("Null")==true){
                    arrayList.get(holder.getAdapterPosition()).setStatus("True");
                    holder.mwPresent.setBackgroundResource(R.drawable.layout_bottom_left_round_rect_green);
                    holder.mwAbsent.setBackgroundResource(R.drawable.layout_bottom_right_round_rect_black);
                    double newOverallRating = calculateRating(Integer.parseInt(countRate), Double.parseDouble(overallRate), holder.mwRating.getRating());
                    // update overallrating , count+1 in the database
                    int count=Integer.parseInt(countRate)+1;
                    String newCount = String.valueOf(count);
                    String newAtten = ""+(Integer.parseInt(finalAtten)+1);

                    Map<String,Object> request=new HashMap<>();
                    request.put("countRating",newCount);
                    request.put("overallRating",roundTo2Decimal(String.valueOf(newOverallRating)));
                    request.put("attendance",newAtten);
                    request.put("status", "True");
                    request.put("lastAttendanceDate", getTodayDate());
                    holder.setData(heading, roundTo2Decimal(String.valueOf(newOverallRating)), newAtten, "True");
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
                    Map<String,Object> request=new HashMap<>();
                    request.put("status", "False");
                    request.put("lastAttendanceDate", getTodayDate());
                    holder.setData(heading, overallRate, finalAtten, "False");
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
        TextView mwItemId, mwAttendance, mwCurrentRating;
        RatingBar mwRating;
        Button mwPresent, mwAbsent;
        public ManageWorkerViewHolder(@NonNull View itemView) {
            super(itemView);
            mwItemId = itemView.findViewById(R.id.manageWorkerItemId);
            mwPresent = itemView.findViewById(R.id.manageWorkerItemPresent);
            mwAbsent = itemView.findViewById(R.id.manageWorkerItemAbsent);
            mwRating = itemView.findViewById(R.id.manageWorkerRating);
            mwAttendance = itemView.findViewById(R.id.manageWorkerCurrentAttendance);
            mwCurrentRating = itemView.findViewById(R.id.manageWorkerCurrentRating);
        }

        public void setData(String heading, String rate, String atten, String stat) {
            mwItemId.setText(heading);
            mwCurrentRating.setText(rate);
            mwAttendance.setText(atten);

            if(stat.equals("True")==true){
                mwPresent.setBackgroundResource(R.drawable.layout_bottom_left_round_rect_green);
                mwAbsent.setBackgroundResource(R.drawable.layout_bottom_right_round_rect_black);
            } else if(stat.equals("False")==true){
                mwPresent.setBackgroundResource(R.drawable.layout_bottom_left_round_rect_black);
                mwAbsent.setBackgroundResource(R.drawable.layout_bottom_right_round_rect_green);
            } else {
                mwPresent.setBackgroundResource(R.drawable.layout_bottom_left_round_rect_white);
                mwAbsent.setBackgroundResource(R.drawable.layout_bottom_right_round_rect_white);
            }
        }
    }
}
