package com.example.da.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da.Dao.SachDao;
import com.example.da.DoiTuong.Sach;
import com.example.da.R;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends BaseAdapter  implements Filterable {
    private int layout;
    List<Sach> bookList;
    List<Sach> list;
    Filter filter;
    SachDao bookDB;
    Context context;
    Sach book;

    private LayoutInflater layoutInflater;

    public BookAdapter( List<Sach> bookList, Context context) {

        this.bookList = bookList;
        this.list = bookList;
        this.context = context;

        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        bookDB = new SachDao(context);
        convertView = layoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        final EditText edMaAdapter = convertView.findViewById(R.id.ed_adapterMaSach);
        ImageView imgBoolDelete = convertView.findViewById(R.id.img_adapterDeleteSach);
        book = bookList.get(position);
        imgBoolDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookDB.deleteSachByID(bookList.get(position).getMaSach());
                Sach book = bookList.get(position);
                bookList.remove(book);
                notifyDataSetChanged();
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "update ở đây !!", Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.updatebook);
                final EditText edMa = dialog.findViewById(R.id.ed_updateboookMaSach);
                final  EditText edTen = dialog.findViewById(R.id.ed_updatebookTenSach);
                final  EditText edTl = dialog.findViewById(R.id.ed_updatebookTheLoai);
                final  EditText edTacGia = dialog.findViewById(R.id.ed_updatebookTacGia);
                final  EditText edNXB = dialog.findViewById(R.id.ed_updatebookNXB);
                final  EditText edGia = dialog.findViewById(R.id.ed_updatebookGia);
                final  EditText edSl = dialog.findViewById(R.id.ed_updatebookSoLuong);
                final ImageView imgUpdateBook = dialog.findViewById(R.id.img_updatebookUpdate);
                edMa.setEnabled(false);
                book = bookList.get(position);
                edMa.setText(book.getMaSach());
                edTen.setText(book.getTenSach());
                edTl.setText(book.getMaTheLoai());
                edTacGia.setText(book.getTacGia());
                edNXB.setText(book.getNXB());
                edGia.setText(String.valueOf(book.getGiaBia()) );
                edSl.setText(String.valueOf(book.getSoLuong()));
                imgUpdateBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        book = bookList.get(position);
                        book.setMaSach(edMa.getText().toString());
                        book.setTenSach(edTen.getText().toString());
                        book.setMaTheLoai(edTl.getText().toString());
                        book.setTacGia(edTacGia.getText().toString());
                        book.setNXB(edNXB.getText().toString());
                        book.setGiaBia(Double.parseDouble(edGia.getText().toString()));
                        book.setSoLuong(Integer.parseInt(edSl.getText().toString()));
                        bookDB = new SachDao(context);
                        long update = bookDB.updateSach(book,bookList.get(position).getMaSach());
                        if (update > 0){
                            Toast.makeText(context, "Update Thành công !!", Toast.LENGTH_SHORT).show();
                            bookList.set(position,book);
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(context, "Update không thành công !!", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                dialog.show();

            }
        });
        book = bookList.get(position);
        edMaAdapter.setText(book.getMaSach());


        return convertView;
    }

    //search Book
    @Override
    public Filter getFilter() {
        if (filter ==null){
            filter  = new CustomFilter();
        }
        return filter;
    }
    public void resetData(){
        bookList = list;
    }
    public class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint==null || constraint.length()==0){
                results.values = list;
                results.count = list.size();
            }else {
                List<Sach> modelHoadonList = new ArrayList<>();
                for (Sach p: bookList){
                    if (p.getMaSach().toUpperCase().startsWith(constraint.toString().toUpperCase())){
                        modelHoadonList.add(p);
                    }
                }
                results.values = modelHoadonList;
                results.count = modelHoadonList.size();
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count==0){
                notifyDataSetInvalidated();
            }else {
                bookList = (List<Sach>)results.values;
                notifyDataSetChanged();
            }
        }
    }

//    List<Sach> arrSach;
//    List<Sach> arrSortSach;
//    private Filter sachFilter;
//    public Activity context;
//    public LayoutInflater inflater;
//    SachDao sachDAO;
//    public BookAdapter(Activity context, List<Sach> arraySach) {
//        super();
//        this.context = context;
//        this.arrSach = arraySach;
//        this.arrSortSach = arraySach;
//        this.inflater =
//                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        sachDAO = new SachDao(context);
//    }
//    @Override
//    public int getCount() {
//        return arrSach.size();
//    }
//    @Override
//    public Object getItem(int position) {
//        return arrSach.get(position);
//    }
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//    public static class ViewHolder {
//        ImageView img;
//        TextView txtBookName;
//        TextView txtBookPrice;
//        TextView txtSoLuong;
//        ImageView imgDelete;
//    }
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        ViewHolder holder;
//        if(convertView==null)
//        {
//            holder = new ViewHolder();
//            convertView = inflater.inflate(R.layout.item_book, null);
//            holder.img = (ImageView) convertView.findViewById(R.id.ivIcon);
//            holder.txtBookName = (TextView)convertView.findViewById(R.id.txtBookName);
//            holder.txtBookPrice = (TextView)convertView.findViewById(R.id.txtBookPrice);
//            holder.txtSoLuong= (TextView) convertView.findViewById(R.id.tvSoLuong);
//            holder.imgDelete = (ImageView)convertView.findViewById(R.id.ivDelete);
//            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    sachDAO.deleteSachByID(arrSach.get(position).getMaSach());
//                    arrSach.remove(position);
//                    notifyDataSetChanged();
//                }
//            });
//            convertView.setTag(holder);
//        }
//        else
//            holder=(ViewHolder)convertView.getTag();
//        Sach _entry = (Sach) arrSach.get(position);
//        holder.img.setImageResource(R.drawable.bookicon);
//        holder.txtBookName.setText("Mã sách: "+_entry.getMaSach());
//        holder.txtSoLuong.setText("Số lượng: "+_entry.getSoLuong());
//        holder.txtBookPrice.setText("Giá: "+ _entry.getGiaBia()+"");
//        return convertView;
//    }
//    @Override
//    public void notifyDataSetChanged() {
//        super.notifyDataSetChanged();
//    }
//    public void changeDataset(List<Sach> items){
//        this.arrSach = items;
//        notifyDataSetChanged();
//    }
//    public void resetData() {
//        arrSach = arrSortSach;
//    }
//    public Filter getFilter() {
//        if (sachFilter == null)
//            sachFilter = new CustomFilter();
//        return sachFilter;
//    }
//    private class CustomFilter extends Filter {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            FilterResults results = new FilterResults();
//            // We implement here the filter logic
//            if (constraint == null || constraint.length() == 0) {
//                results.values = arrSortSach;
//                results.count = arrSortSach.size();
//            }
// else {
//                List<Sach> lsSach = new ArrayList<Sach>();
//                for (Sach p : arrSach) {
//                    if
//                    (p.getMaSach().toUpperCase().startsWith(constraint.toString().toUpperCase()))
//                        lsSach.add(p);
//                }
//                results.values = lsSach;
//                results.count = lsSach.size();
//            }
//            return results;
//        }
//        @Override
//        protected void publishResults(CharSequence constraint,
//                                      FilterResults results) {
//            if (results.count == 0)
//                notifyDataSetInvalidated();
//            else {
//                arrSach = (List<Sach>) results.values;
//                notifyDataSetChanged();
//            }
//        }
//    }
}
