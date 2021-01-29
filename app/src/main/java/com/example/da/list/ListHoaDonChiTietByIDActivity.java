package com.example.da.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.da.Dao.HoaDonChiTietDao;
import com.example.da.DoiTuong.HoaDonChiTiet;
import com.example.da.R;
import com.example.da.adapter.CartAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListHoaDonChiTietByIDActivity extends AppCompatActivity {
        public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
        ListView lvCart;
        CartAdapter adapter = null;
        HoaDonChiTietDao hoaDonChiTietDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don_chi_tiet_by_i_d);
        setTitle("HOÁ ĐƠN CHI TIẾT");
        setContentView(R.layout.activity_list_hoa_don_chi_tiet_by_i_d);
        lvCart = (ListView) findViewById(R.id.lvHoaDonChiTiet);
        hoaDonChiTietDAO = new HoaDonChiTietDao(ListHoaDonChiTietByIDActivity.this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            dsHDCT =hoaDonChiTietDAO.getAllHoaDonChiTietByID(b.getString("MAHOADON"));
        }
        adapter = new CartAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);
    }
}