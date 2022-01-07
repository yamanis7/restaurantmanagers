package com.example.restaurantmanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseStok extends SQLiteOpenHelper {

    private Context konteks;
    private static final String DB_NAMA = "Stok.db";
    private static final int DB_VERSI = 1;

    private static final String NAMA_LIST = "Stok_Lib";
    private static final String KOLOM_ID = "_id";
    private static final String NAMA_ITEM = "item";
    private static final String DATA_JUMLAH = "jumlah";

    MyDatabaseStok(@Nullable Context konteks) {
        super(konteks, DB_NAMA, null, DB_VERSI);
        this.konteks = konteks;
    }

    @Override
    public void onCreate(SQLiteDatabase sql) {
        String query = "CREATE TABLE " + NAMA_LIST +
                " (" + KOLOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAMA_ITEM + " TEXT, " +
                DATA_JUMLAH + " INTEGER);";
        sql.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sql, int i, int i1) {
        sql.execSQL("DROP TABLE IF EXISTS " + NAMA_LIST);
        onCreate(sql);
    }

    void addstok(String bahan, int jumlahbh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAMA_ITEM, bahan);
        cv.put(DATA_JUMLAH, jumlahbh);
        long result = db.insert(NAMA_LIST, null, cv);
        if (result == -1) {
            Toast.makeText(konteks, "Gagal", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(konteks, "Berhasil menambahkan", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor baca_data() {
        String query = "SELECT * FROM " + NAMA_LIST;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor  = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void UpdateData(String baris_id, String bahan, String jumlah_item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAMA_ITEM, bahan);
        cv.put(DATA_JUMLAH, jumlah_item);

        long result = db.update(NAMA_LIST, cv, "_id=?", new String[]{baris_id});
        if (result == -1) {
            Toast.makeText(konteks, "Update gagal", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(konteks, "Update berhasil", Toast.LENGTH_SHORT).show();
        }
    }

    void hapusData(String baris_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(NAMA_LIST, "_id=?", new String[]{baris_id});
        if (result == -1) {
            Toast.makeText(konteks, "Gagal menghapus", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(konteks, "Berhasil menghapus", Toast.LENGTH_SHORT).show();
        }
    }
}
