package com.example.messe;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterSpecialRequest extends RecyclerView.Adapter<AdapterSpecialRequest.SpecialRequestViewHolder> {

    private ArrayList<ItemSpecialRequest> arrayList;
    public AdapterSpecialRequest(ArrayList<ItemSpecialRequest> arrayList){
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public AdapterSpecialRequest.SpecialRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special_request, parent, false);
        return new SpecialRequestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSpecialRequest.SpecialRequestViewHolder holder, int position) {
        String fName = arrayList.get(position).getFirstName();
        String lName = arrayList.get(position).getLastName();
        String rId = arrayList.get(position).getReqId();
        String rMessage = arrayList.get(position).getReqMessage();
        String stat = arrayList.get(position).getStatus();

        String heading = "" + fName + " " + lName + " (" + rId +")";
        String body = "" + rMessage;

        holder.setData(heading, body, stat);
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
