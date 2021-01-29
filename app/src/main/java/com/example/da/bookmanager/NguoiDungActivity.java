package com.example.da.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.da.Dao.NguoiDungDao;
import com.example.da.DoiTuong.NguoiDung;
import com.example.da.R;
import com.example.da.list.ListNguoiDungActivity;

public class NguoiDungActivity extends AppCompatActivity {
    Button btnThemNguoiDung;
    NguoiDungDao nguoiDungDao;
    EditText edUser, edPass,edRePass, edPhone, edFullName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        setTitle("THÊM NGƯỜI DÙNG");
        btnThemNguoiDung = (Button) findViewById(R.id.btnShowUser);
        edUser = (EditText) findViewById(R.id.edUserName1);
        edPass = (EditText) findViewById(R.id.edPassword1);
        edPhone = (EditText) findViewById(R.id.edPhone);
        edFullName = (EditText) findViewById(R.id.edFullName);
        edRePass = (EditText) findViewById(R.id.edPassword);
        btnThemNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( NguoiDungActivity.this, ListNguoiDungActivity.class);
                startActivity(intent);
            }
        });
    }
    public void showUsers(View view) {
        finish();
    }
    public void addUser(View view) {
        nguoiDungDao = new NguoiDungDao(NguoiDungActivity.this);
        NguoiDung user = new NguoiDung(edUser.getText().toString(),
                edPass.getText().toString(),
                edPhone.getText().toString(), edFullName.getText().toString());
        try {
            if (validateForm()>0){
                if (nguoiDungDao.inserNguoiDung(user) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    public int validateForm(){
        int check = 1;
        if (edUser.getText().length() == 0 || edFullName.getText().length() == 0
                || edPhone.getText().length() == 0 || edPass.getText().length()==0
                || edRePass.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông ",
                    Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (!pass.equals(rePass)) {
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp",
                        Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;


    }
}