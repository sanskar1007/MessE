package com.example.messe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.logging.Handler;

public class AdapterInventory extends RecyclerView.Adapter<AdapterInventory.InventoryViewHolder>{
    private ArrayList<ItemInventory> arrayList;
    public AdapterInventory(ArrayList<ItemInventory> arrayList) {this.arrayList = arrayList;}

    public ArrayList<ItemInventory> getArrayList() {
        return arrayList;
    }

    @NonNull
    @Override
    public AdapterInventory.InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory, parent, false);
        return new AdapterInventory.InventoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterInventory.InventoryViewHolder holder, int position) {
        String nameItem = arrayList.get(position).getName();
        String quantityItem = arrayList.get(position).getQuantity();
        String typeItem = arrayList.get(position).getType();

        holder.setData(nameItem, quantityItem, "0");

        holder.miIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(arrayList.get(holder.getAdapterPosition()).getCount())+1;
                arrayList.get(holder.getAdapterPosition()).setCount(""+count);
                holder.setData(nameItem, quantityItem,""+count);
            }
        });

        holder.miDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(arrayList.get(holder.getAdapterPosition()).getCount())-1;
                if(count<0)
                    count = 0;
                arrayList.get(holder.getAdapterPosition()).setCount(""+count);
                holder.setData(nameItem, quantityItem,""+count);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class InventoryViewHolder extends RecyclerView.ViewHolder{
        TextView miName, miCurrQuantity, miCount;
        Button miIncrease, miDecrease;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            miName = itemView.findViewById(R.id.inventoryItemName);
            miCurrQuantity = itemView.findViewById(R.id.inventoryItemCurrentQuantity);
            miCount = itemView.findViewById(R.id.inventoryItemCount);
            miIncrease = itemView.findViewById(R.id.inventoryItemIncreaseButton);
            miDecrease = itemView.findViewById(R.id.inventoryItemDecreaseButton);
        }

        public void setData(String nameItem, String quantityItem, String countItem) {
            miName.setText(nameItem);
            miCurrQuantity.setText(quantityItem);
            miCount.setText(countItem);
        }
    }
}
