package com.example.da;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.da.Dao.NguoiDungDao;

public class Login extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button btnLogin, btnCancel;
    CheckBox chkRememberPass;
    String strUser, strPass;
    NguoiDungDao nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("ĐĂNG NHẬP");
        edUserName = (EditText) findViewById(R.id.edUserName);
        edPassword = (EditText) findViewById(R.id.edPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        chkRememberPass = (CheckBox) findViewById(R.id.chkRememberPass);
        nguoiDungDAO = new NguoiDungDao(Login.this);
        chkRememberPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String edName = edUserName.getText().toString();
//                String edPass = edPassword.getText().toString();
//                if(edName.equalsIgnoreCase("") || edPass.equalsIgnoreCase("")){
//                    Toast.makeText(Login.this, "Vui lòng điền thông tin User và Pass !!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if (chkRememberPass.isChecked()==true){
                    Toast.makeText(Login.this, "Đã lưu mật khẩu", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Login.this, "Không lưu mật khẩu", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edName = edUserName.getText().toString();
                String edPass = edPassword.getText().toString();
                if(edName.equalsIgnoreCase("") || edPass.equalsIgnoreCase("")){
                    Toast.makeText(Login.this, "Vui lòng điền thông tin User và Pass !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}