package com.example.da.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.da.ChangePasswordActivity;
import com.example.da.Dao.NguoiDungDao;
import com.example.da.DoiTuong.NguoiDung;
import com.example.da.MainActivity;
import com.example.da.R;
import com.example.da.adapter.NguoiDungAdapter;
import com.example.da.bookmanager.NguoiDungActivity;
import com.example.da.bookmanager.NguoiDungDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ListNguoiDungActivity extends AppCompatActivity {
    public static List<NguoiDung> dsNguoiDung = new ArrayList<>();
    ListView lvNguoiDung;
    NguoiDungAdapter adapter = null;
    NguoiDungDao nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nguoi_dung);

        setTitle("NGƯỜI DÙNG");
        setContentView(R.layout.activity_list_nguoi_dung);
        lvNguoiDung = (ListView) findViewById(R.id.lvNguoiDung);
        nguoiDungDAO = new NguoiDungDao(ListNguoiDungActivity.this);
        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();
        adapter = new NguoiDungAdapter(this, dsNguoiDung);
        lvNguoiDung.setAdapter(adapter);
        lvNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new
                        Intent(ListNguoiDungActivity.this, NguoiDungDetailActivity.class);
                Bundle b = new Bundle();
                b.putString("USERNAME", dsNguoiDung.get(position).getUserName());
                b.putString("PHONE", dsNguoiDung.get(position).getPhone());
                b.putString("FULLNAME", dsNguoiDung.get(position).getHoTen());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        lvNguoiDung.setOnItemLongClickListener(new
             AdapterView.OnItemLongClickListener() {
              @Override
             public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
             return false;
         }
      });
    }
    @Override
    protected void onResume() {
        super.onResume();
        dsNguoiDung.clear();
        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();
        adapter.changeDataset(nguoiDungDAO.getAllNguoiDung());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()) {
            case R.id.add:
                intent = new
                        Intent(ListNguoiDungActivity.this, NguoiDungActivity.class);
                startActivity(intent);
                return(true);
            case R.id.changePass:
                intent = new
                        Intent(ListNguoiDungActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                return(true);
            case R.id.logOut:
                SharedPreferences pref =
                        getSharedPreferences("USER_FILE",MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                //xoa tinh trang luu tru truoc do
                edit.clear();
                edit.commit();
                intent = new Intent(ListNguoiDungActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    }
