package com.example.da.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da.Dao.TheLoaiDao;
import com.example.da.DoiTuong.TheLoai;
import com.example.da.R;
import com.example.da.list.ListTheLoaiActivity;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiActivity extends AppCompatActivity {
    Button btnThemTheLoai;
    TheLoaiDao theloaiDAO;
    EditText edMa, edTen, edVi, edMo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THÊM THỂ LOẠI");
        setContentView(R.layout.activity_the_loai);


        btnThemTheLoai = findViewById(R.id.btn_themCategory);
        edMa = findViewById(R.id.ed_maTheLoai);
        edTen = findViewById(R.id.ed_tenTheLoai);
        edVi = findViewById(R.id.ed_viTriTheLoai);
        edMo = findViewById(R.id.ed_moTaTheLoai);
        TextView text = findViewById(R.id.text);
    }

    public void showtheloai(View view) {
        finish();
    }

    public void quaylai(View view) {
        onBackPressed();
    }

    public void themtheloai(View view) {
        theloaiDAO = new TheLoaiDao(TheLoaiActivity.this);
        TheLoai user = new TheLoai(edMa.getText().toString(), edTen.getText().toString(), edVi.getText().toString(), edMo.getText().toString());
        try {
            if (validateForm() > 0) {
                if (theloaiDAO.inserTheLoai(user) > 0) {
                    onBackPressed();
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Lỗi:", ex.toString());
        }
    }

    public int validateForm() {
        int check = 1;
        if (edMa.getText().length() == 0 || edTen.getText().length() == 0
                || edVi.getText().length() == 0 || edMo.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông tin ",
                    Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void xemlistview(View view) {
        Intent intent=new Intent(TheLoaiActivity.this,ListTheLoaiActivity.class);
        startActivity(intent);
    }

    }