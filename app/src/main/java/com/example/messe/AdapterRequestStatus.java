package com.example.messe;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRequestStatus extends RecyclerView.Adapter<AdapterRequestStatus.RequestStatusViewHolder> {
    private ArrayList<ItemSpecialRequest> arrayList;
    public AdapterRequestStatus(ArrayList<ItemSpecialRequest> arrayList){
        this.arrayList=arrayList;
    }




    @NonNull
    @Override
    public AdapterRequestStatus.RequestStatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_request_status, parent, false);
        return new RequestStatusViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRequestStatus.RequestStatusViewHolder holder, int position) {
        final String rDate = arrayList.get(position).getDate();
        final String rMessage = arrayList.get(position).getReqMessage();
        final String stat = arrayList.get(position).getStatus();
        holder.setDate(rDate, rMessage, stat);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RequestStatusViewHolder extends RecyclerView.ViewHolder{
        TextView rsDate, rsReqMessage, rsStatus;
       public RequestStatusViewHolder(@NonNull View itemView) {
            super(itemView);
            rsDate = itemView.findViewById(R.id.requestStatusItemDate);
            rsReqMessage = itemView.findViewById(R.id.requestStatusItemReqMessage);
            rsStatus = itemView.findViewById(R.id.requestStatusItemStatus);
        }

        public void setDate(String rDate, String rMessage, String stat) {
            rsDate.setText(rDate);
            rsReqMessage.setText(rMessage);
            if(stat.equals("Null")){
                rsStatus.setBackgroundResource(R.drawable.layout_bottom_round_rect_white);
                rsStatus.setText("STATUS: NULL");
            } else if(stat.equals("True")){
                rsStatus.setBackgroundResource(R.drawable.layout_bottom_round_rect_green);
                rsStatus.setText("STATUS: ACCEPTED");
                rsStatus.setTextColor(Color.WHITE);
            } if(stat.equals("False")){
                rsStatus.setBackgroundResource(R.drawable.layout_bottom_round_rect_black);
                rsStatus.setText("STATUS: DECLINED");
                rsStatus.setTextColor(Color.WHITE);
            }
        }
    }
}
