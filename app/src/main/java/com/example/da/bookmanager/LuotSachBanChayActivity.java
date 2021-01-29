package com.example.da.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.da.Dao.SachDao;
import com.example.da.DoiTuong.Sach;
import com.example.da.R;
import com.example.da.adapter.BookAdapter;

import java.util.ArrayList;
import java.util.List;

public class LuotSachBanChayActivity extends AppCompatActivity {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    BookAdapter adapter = null;
    SachDao sachDAO;
    EditText edThang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luot_sach_ban_chay);

        setTitle("TOP 10 SÁCH BÁN CHẠY");
        setContentView(R.layout.activity_luot_sach_ban_chay);
       lvBook = (ListView) findViewById(R.id.lvBookTop);
       edThang = (EditText) findViewById(R.id.edThang);
    }
    public void VIEW_SACH_TOP_10(View view){
        if (Integer.parseInt(edThang.getText().toString())>13 ||
                Integer.parseInt(edThang.getText().toString())<0){
            Toast.makeText(getApplicationContext(),"Không đúng định dạng tháng (1-12)",Toast.LENGTH_SHORT).show();

        }else {
            sachDAO = new SachDao(LuotSachBanChayActivity.this);
            dsSach = sachDAO.getSachTop10(edThang.getText().toString());
            adapter = new BookAdapter(dsSach, this);
            lvBook.setAdapter(adapter);
        }
    }
}