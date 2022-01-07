package com.example.restaurantmanagers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapt extends RecyclerView.Adapter<CustomAdapt.MyViewHolder> {

    private Context context;
    private ArrayList kolom_id, nama_item, jumlah_item;
    Activity activity;

    CustomAdapt(Activity activity, Context context, ArrayList kolom_id, ArrayList nama_item, ArrayList jumlah_item) {
        this.activity = activity;
        this.context = context;
        this.kolom_id = kolom_id;
        this.nama_item = nama_item;
        this.jumlah_item = jumlah_item;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.array_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.kolom_idtv.setText(String.valueOf(kolom_id.get(position)));
        holder.nama_itemtv.setText(String.valueOf(nama_item.get(position)));
        holder.jumlah_itemtv.setText(String.valueOf(jumlah_item.get(position)));
        holder.mainList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateList.class);
                intent.putExtra("id", String.valueOf(kolom_id.get(position)));
                intent.putExtra("nama", String.valueOf(nama_item.get(position)));
                intent.putExtra("jumlah", String.valueOf(jumlah_item.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kolom_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView kolom_idtv, nama_itemtv, jumlah_itemtv;
        LinearLayout mainList;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            kolom_idtv = itemView.findViewById(R.id.kolom_idtv);
            nama_itemtv = itemView.findViewById(R.id.nama_itemtv);
            jumlah_itemtv = itemView.findViewById(R.id.jumlah_itemtv);
            mainList = itemView.findViewById(R.id.mainList);
        }
    }
}
