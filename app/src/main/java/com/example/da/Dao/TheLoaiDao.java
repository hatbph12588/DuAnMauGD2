package com.example.da.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.da.DatabaseHelper;
import com.example.da.DoiTuong.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "TheLoai";
    public static final String SQL_THE_LOAI ="CREATE TABLE TheLoai (matheloai text primary key, tentheloai text, mota text, vitri int);";

    public static final String TAG = "TheLoaiDAO";
    public TheLoaiDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //insert
    public int inserTheLoai(TheLoai theLoai){
        ContentValues values = new ContentValues();
        values.put("matheloai",theLoai.getMaTheLoai());
        values.put("tentheloai",theLoai.getTenTheLoai());
        values.put("mota",theLoai.getMoTa());
        values.put("vitri",theLoai.getViTri());
        try {
            if(db.insert(TABLE_NAME,null,values)== -1){
                return -1;
            }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }
        return 1;
    }
    //getAllTheLoai
    public List<TheLoai> getAllTheLoai(){
        List<TheLoai> dsTheLoai = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            TheLoai ee = new TheLoai();
            ee.setMaTheLoai(c.getString(0));
            ee.setTenTheLoai(c.getString(1));
            ee.setMoTa(c.getString(2));
            ee.setViTri(c.getString(3));
            dsTheLoai.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsTheLoai;
//        List<TheLoai> dsTheLoai = new ArrayList<>();
//        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
//        c.moveToFirst();
//        while (c.isAfterLast()==false){
//            TheLoai ee = new TheLoai();
//            ee.setMaTheLoai(c.getString(0));
//            ee.setTenTheLoai(c.getString(1));
//            ee.setMoTa(c.getString(2));
//            ee.setViTri(c.getInt(3));
//            dsTheLoai.add(ee);
//            Log.d("//=====",ee.toString());
//            c.moveToNext();
//        }
//        c.close();
//        return dsTheLoai;
    }
    public List<String> getCategories(){
        List<String> categoryList = new ArrayList<>();
        String query = "SELECT "+"matheloai"+","+"tentheloai"+" from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            String ma = cursor.getString(0);
            String ten = cursor.getString(1);
            String thietLap = ma +" | "+ten;
            categoryList.add(thietLap);
            cursor.moveToNext();
        }
        cursor.close();
        return categoryList;
    }
    //update
    public int updateTheLoai(TheLoai theLoai){
        ContentValues values = new ContentValues();
        values.put("matheloai",theLoai.getMaTheLoai());
        values.put("tentheloai",theLoai.getTenTheLoai());
        values.put("mota",theLoai.getMoTa());
        values.put("vitri",theLoai.getViTri());
        int result = db.update(TABLE_NAME,values,"matheloai=?", new
                String[]{theLoai.getMaTheLoai()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    //delete
    public int deleteTheLoaiByID(String matheloai){
        int result = db.delete(TABLE_NAME,"matheloai=?",new String[]{matheloai});
        if (result == 0)
            return -1;
        return 1;
    }
}
