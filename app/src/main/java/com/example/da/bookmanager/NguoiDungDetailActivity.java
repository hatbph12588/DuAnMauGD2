package com.example.da.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.da.Dao.NguoiDungDao;
import com.example.da.R;

public class NguoiDungDetailActivity extends AppCompatActivity {
    EditText edFullName, edPhone;
    NguoiDungDao nguoiDungDAO;
    String username,fullname,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung_detail);
        setTitle("CHI TIẾT NGƯỜI DÙNG");
        setContentView(R.layout.activity_nguoi_dung_detail);
        edFullName = (EditText) findViewById(R.id.edFullName);
        edPhone = (EditText) findViewById(R.id.edPhone);
        nguoiDungDAO = new NguoiDungDao(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        fullname = b.getString("FULLNAME");
        phone = b.getString("PHONE");
        username = b.getString("USERNAME");
        edFullName.setText(fullname);
        edPhone.setText(phone);
    }
    public void updateUser(View view){
        if
        (nguoiDungDAO.updateInfoNguoiDung(username,edPhone.getText().toString(),edFullName.getText().toString())>0)
                {
            Toast.makeText(getApplicationContext(),"Lưu thành công",Toast.LENGTH_SHORT).show();
        }

    }
    public void Huy(View view){
        finish();
    }
    }