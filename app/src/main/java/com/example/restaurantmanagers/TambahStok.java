package com.example.restaurantmanagers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.restaurantmanagers.R;

public class TambahStok extends AppCompatActivity {

    EditText bahan, jumlahbahan;
    Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_stok);

        bahan = (EditText) findViewById(R.id.bahan);
        jumlahbahan = (EditText) findViewById(R.id.jumlahbahan);

        submit_btn = (Button) findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseStok mydb = new MyDatabaseStok(TambahStok.this);
                mydb.addstok(bahan.getText().toString().trim(), Integer.valueOf(jumlahbahan.getText().toString().trim()));
            }
        });
    }
}