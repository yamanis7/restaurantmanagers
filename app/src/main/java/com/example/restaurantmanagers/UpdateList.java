package com.example.restaurantmanagers;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateList extends AppCompatActivity {

    EditText bahan2, jumlahbahan2;
    Button update_btn, hapus_btn;

    String id, nama, jumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_list);

        bahan2 = findViewById(R.id.bahan2);
        jumlahbahan2 = findViewById(R.id.jumlahbahan2);
        update_btn = findViewById(R.id.update_btn);
        hapus_btn = findViewById(R.id.hapus_btn);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nama);
        }

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseStok mydb = new MyDatabaseStok(UpdateList.this);
                nama = bahan2.getText().toString().trim();
                jumlah = jumlahbahan2.getText().toString().trim();
                mydb.UpdateData(id, nama, jumlah);
            }
        });

        hapus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                konfirmasi();
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("nama") && getIntent().hasExtra("jumlah")) {
            id = getIntent().getStringExtra("id");
            nama = getIntent().getStringExtra("nama");
            jumlah = getIntent().getStringExtra("jumlah");

            bahan2.setText(nama);
            jumlahbahan2.setText(jumlah);
        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void konfirmasi() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus " + nama);
        builder.setMessage("Yakin ingin menghapus " + nama + " ?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseStok mydb = new MyDatabaseStok(UpdateList.this);
                mydb.hapusData(id);
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}