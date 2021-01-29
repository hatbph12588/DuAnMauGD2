package com.example.da.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.da.Dao.HoaDonChiTietDao;
import com.example.da.R;

public class ThongKeDoanhThuActivity extends AppCompatActivity {
    TextView tvNgay, tvThang, tvNam;
    HoaDonChiTietDao hoaDonChiTietDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_doanh_thu);
        setTitle("DOANH THU");
        setContentView(R.layout.activity_thong_ke_doanh_thu);
        tvNgay = (TextView) findViewById(R.id.tvThongKeNgay);
        tvThang = (TextView) findViewById(R.id.tvThongKeThang);
        tvNam = (TextView) findViewById(R.id.tvThongKeNam);
        hoaDonChiTietDAO = new HoaDonChiTietDao(this);
        tvNgay.setText("Hôm nay: "+hoaDonChiTietDAO.getDoanhThuTheoNgay());
        tvThang.setText("Tháng này: "+hoaDonChiTietDAO.getDoanhThuTheoThang());
        tvNam.setText("Năm này: "+hoaDonChiTietDAO.getDoanhThuTheoNam());
    }
}