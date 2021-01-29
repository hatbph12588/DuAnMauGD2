package com.example.da.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.da.Dao.SachDao;
import com.example.da.Dao.TheLoaiDao;
import com.example.da.DoiTuong.Sach;
import com.example.da.DoiTuong.TheLoai;
import com.example.da.R;
import com.example.da.list.ListBookActivity;

import java.util.ArrayList;
import java.util.List;

public class ThemSachActivity extends AppCompatActivity {
    Button btnLoginSachAdd,btnLoginSachHuy,btnLoginSachShow;
    Spinner spinnerLoginSach;
    String theloaiSach;
    ImageView imgAddTheLoaiSach;
    SachDao bookDB;
    TheLoaiDao categoryDB;
    EditText edLoginSachId ,edLoginSachTen,edLoginSachNXB,edLoginSachTG,edLoginSachGia,edLoginSachSL;
    List<TheLoai> categoryList ;
    List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sach);
        spinnerLoginSach = findViewById(R.id.sp_loginSach);
        imgAddTheLoaiSach = findViewById(R.id.img_addTheLoaiSach);
        edLoginSachId = findViewById(R.id.ed_loginSachId);
        edLoginSachTen = findViewById(R.id.ed_loginSachTen);
        edLoginSachNXB = findViewById(R.id.ed_loginSachNxb);
        edLoginSachTG = findViewById(R.id.ed_loginSachTacGia);
        edLoginSachGia = findViewById(R.id.ed_loginSachGia);
        edLoginSachSL = findViewById(R.id.ed_loginSachSoLuong);
        btnLoginSachAdd = findViewById(R.id.btn_loginSachAdd);
        btnLoginSachHuy = findViewById(R.id.btn_loginSachCancel);
        btnLoginSachShow = findViewById(R.id.btn_loginSachShow);

        stringList = new ArrayList<>();
        categoryDB = new TheLoaiDao(this);
        stringList = categoryDB.getCategories();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,stringList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerLoginSach.setAdapter(adapter);

        spinnerLoginSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                theloaiSach = spinnerLoginSach.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imgAddTheLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemSachActivity.this,TheLoai.class);
                startActivity(intent);
            }
        });

        btnLoginSachAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = edLoginSachId.getText().toString();
                String ten = edLoginSachTen.getText().toString();
                String tg = edLoginSachTG.getText().toString();
                String nxb = edLoginSachNXB.getText().toString();
                int sl = Integer.parseInt(edLoginSachSL.getText().toString());
                double gia = Double.parseDouble(edLoginSachGia.getText().toString());
                if(ma.equals("") || ten.equals("") || tg.equals("") || nxb.equals("")
                        || String.valueOf(sl).equals("") || String.valueOf(gia).equals("") ){
                    Toast.makeText(ThemSachActivity.this, "Không được để trống thông tin, vui lòng điền đầy đủ !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                bookDB = new SachDao(ThemSachActivity.this);
                Sach book = new Sach();
                book.setMaSach(edLoginSachId.getText().toString());
                book.setMaTheLoai(spinnerLoginSach.getSelectedItem().toString());
                book.setTenSach(edLoginSachTen.getText().toString());
                book.setTacGia(edLoginSachTG.getText().toString());
                book.setNXB(edLoginSachNXB.getText().toString());
                book.setGiaBia(Double.parseDouble(edLoginSachGia.getText().toString()));
                book.setSoLuong(Integer.parseInt(edLoginSachSL.getText().toString()));

                if(bookDB.inserSach(book) > 0){
                    Toast.makeText(ThemSachActivity.this, "Thành công !!", Toast.LENGTH_SHORT).show();
                    edLoginSachId.setText("");
                    edLoginSachTen.setText("");
                    edLoginSachTG.setText("");
                    edLoginSachNXB.setText("");
                    edLoginSachGia.setText("");
                    edLoginSachSL.setText("");

                }else{
                    Toast.makeText(ThemSachActivity.this, "Không thành công !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLoginSachShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemSachActivity.this, ListBookActivity.class);
                startActivity(intent);
            }
        });
    }
//    SachDao sachDAO;
//    TheLoaiDao theLoaiDAO;
//    Spinner spnTheLoai;
//    ImageView imageView;
//    EditText edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
//    String maTheLoai = "";
//    List<TheLoai> listTheLoai = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_them_sach);
//        setTitle("THÊM SÁCH");
//        spnTheLoai = (Spinner) findViewById(R.id.spnTheLoai);
//        getTheLoai();
//        imageView=findViewById(R.id.imageView7);
//        edMaSach = (EditText) findViewById(R.id.edMaSach);
//        edTenSach = (EditText) findViewById(R.id.edTenSach);
//        edNXB = (EditText) findViewById(R.id.edNXB);
//        edTacGia = (EditText) findViewById(R.id.edTacGia);
//        edGiaBia = (EditText) findViewById(R.id.edGiaBia);
//        edSoLuong = (EditText) findViewById(R.id.edSoLuong);
//        //
//        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                maTheLoai = listTheLoai.get(spnTheLoai.getSelectedItemPosition()).getMaTheLoai();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//        //load data into form
//        Intent in = getIntent();
//        Bundle b = in.getExtras();
//        if (b != null) {
//            edMaSach.setText(b.getString("MASACH"));
//            String maTheLoai = b.getString("MATHELOAI");
//            edTenSach.setText(b.getString("TENSACH"));
//            edNXB.setText(b.getString("NXB"));
//            edTacGia.setText(b.getString("TACGIA"));
//            edGiaBia.setText(b.getString("GIABIA"));
//            edSoLuong.setText(b.getString("SOLUONG"));
//            spnTheLoai.setSelection(checkPositionTheLoai(maTheLoai));
//        }
//    }
//
//    public void showSpinner(View view) {
//        sachDAO = new SachDao(ThemSachActivity.this);
//        sachDAO.getAllSach();
//    }
//
//    public void getTheLoai() {
//        theLoaiDAO = new TheLoaiDao(ThemSachActivity.this);
//        listTheLoai = theLoaiDAO.getAllTheLoai();
//        ArrayAdapter<TheLoai> dataAdapter = new ArrayAdapter<TheLoai>(this, android.R.layout.simple_spinner_item, listTheLoai);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnTheLoai.setAdapter(dataAdapter);
//    }
//
//    public void addBook(View view) {
//        sachDAO = new SachDao(ThemSachActivity.this);
//        Sach sach = new
//                Sach(edMaSach.getText().toString(), maTheLoai, edTenSach.getText().toString(),
//                edTacGia.getText().toString(), edNXB.getText().toString(),
//
//                Double.parseDouble(edGiaBia.getText().toString()), Integer.parseInt(edSoLuong.getText
//                ().toString()));
//        try {
//            if (sachDAO.inserSach(sach) > 0) {
//                Toast.makeText(getApplicationContext(), "Thêm thành công",
//                        Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "Thêm thất bại",
//                        Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception ex) {
//            Log.e("Error", ex.toString());
//        }
//    }
//
//    public void showBook(View view) {
//        finish();
//    }
//
//    public int checkPositionTheLoai(String strTheLoai) {
//        for (int i = 0; i < listTheLoai.size(); i++) {
//            if (strTheLoai.equals(listTheLoai.get(i).getMaTheLoai())) {
//                return i;
//            }
//        }
//        return 0;
//    }
//
//    public void listsach(View view) {
//        Intent intent5=new Intent(ThemSachActivity.this, ListBookActivity.class);
//               startActivity(intent5);
//    }
}