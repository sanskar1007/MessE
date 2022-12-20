package com.example.messe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterSpecialRequest extends RecyclerView.Adapter<AdapterSpecialRequest.ViewHolderSpecailRequest> {
    Context context;
    ArrayList<ItemSpecialRequest> specialRequestArrayList;

    public AdapterSpecialRequest(Context context, ArrayList<ItemSpecialRequest> specialRequestArrayList) {
        this.context = context;
        this.specialRequestArrayList = specialRequestArrayList;
    }

    @NonNull
    @Override
    public ViewHolderSpecailRequest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_special_request, parent, false);
        return new ViewHolderSpecailRequest(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSpecailRequest holder, int position) {
        ItemSpecialRequest itemSpecialRequest = specialRequestArrayList.get(position);
        // form the header of the special request
        String id = "" + itemSpecialRequest.firstName + " " + itemSpecialRequest.lastName + " (" + itemSpecialRequest.reqId + ")";

        // add the "id" to the layout
        holder.specialRequestItemId.setText(id);
        // add the "message" to the layout
        holder.specialRequestItemText.setText(itemSpecialRequest.reqMessage);
    }

    @Override
    public int getItemCount() {
        return specialRequestArrayList.size();
    }

    public static class ViewHolderSpecailRequest extends RecyclerView.ViewHolder{
        TextView specialRequestItemId, specialRequestItemText;
        Button specialRequestItemAccept, specialRequestItemDecline;
        public ViewHolderSpecailRequest(@NonNull View itemView) {
            super(itemView);
            specialRequestItemId = itemView.findViewById(R.id.specialRequestItemId);
            specialRequestItemText = itemView.findViewById(R.id.specialRequestItemText);
            specialRequestItemAccept = itemView.findViewById(R.id.specialRequestItemAccept);
            specialRequestItemDecline = itemView.findViewById(R.id.specialRequestItemDecline);
        }
    }
}
