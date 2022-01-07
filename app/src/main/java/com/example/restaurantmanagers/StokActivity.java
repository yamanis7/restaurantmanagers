package com.example.restaurantmanagers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.restaurantmanagers.R;

import java.util.ArrayList;

public class StokActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button add_btn;

    MyDatabaseStok mydb;
    ArrayList<String> kolom_id, nama_item, jumlah_item;

    CustomAdapt customAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stok);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        add_btn = (Button) findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StokActivity.this, TambahStok.class);
                startActivity(intent);
            }
        });

        mydb = new MyDatabaseStok(StokActivity.this);
        kolom_id = new ArrayList<>();
        nama_item = new ArrayList<>();
        jumlah_item = new ArrayList<>();

        tampildata();

        customAdapt = new CustomAdapt(StokActivity.this, this, kolom_id, nama_item, jumlah_item);
        recyclerView.setAdapter(customAdapt);
        recyclerView.setLayoutManager(new LinearLayoutManager(StokActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void tampildata() {
        Cursor cursor = mydb.baca_data();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                kolom_id.add(cursor.getString(0));
                nama_item.add(cursor.getString(1));
                jumlah_item.add(cursor.getString(2));
            }
        }
    }
}