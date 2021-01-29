package com.example.da;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.da.bookmanager.LuotSachBanChayActivity;
import com.example.da.bookmanager.ThongKeDoanhThuActivity;
import com.example.da.list.ListBookActivity;
import com.example.da.list.ListHoaDonActivity;
import com.example.da.list.ListNguoiDungActivity;
import com.example.da.list.ListTheLoaiActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewNguoiDung(View view) {
        Intent intent=new Intent(MainActivity.this, ListNguoiDungActivity.class);
        startActivity(intent);
    }

    public void viewSach(View view) {
        Intent intent2=new Intent(MainActivity.this, ListBookActivity.class);
        startActivity(intent2);
    }

    public void viewTheLoai(View view) {
        Intent intent3=new Intent(MainActivity.this, ListTheLoaiActivity.class);
        startActivity(intent3);
    }

    public void viewHoaDon(View view) {
        Intent intent4=new Intent(MainActivity.this, ListHoaDonActivity.class);
        startActivity(intent4);
    }

    public void viewsachBanChay(View view) {
        Intent intent5=new Intent(MainActivity.this, LuotSachBanChayActivity.class);
        startActivity(intent5);
    }

    public void viewThongKe(View view) {
        Intent intent6=new Intent(MainActivity.this, ThongKeDoanhThuActivity.class);
        startActivity(intent6);
    }
}