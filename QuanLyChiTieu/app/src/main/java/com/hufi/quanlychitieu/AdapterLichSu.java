package com.hufi.quanlychitieu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterLichSu extends ArrayAdapter<ChiTietChiTieu> {
    Context context;
    int layoutResource;
    ArrayList<ChiTietChiTieu> data;
    Database db;

    public AdapterLichSu(@NonNull Context context, int resource, @NonNull ArrayList<ChiTietChiTieu> objects) {
        super(context, resource, objects);
        this.data=objects;
        this.layoutResource=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        ChiTietChiTieu ctct = data.get(position);

        convertView = layoutInflater.inflate(layoutResource, parent, false);
        db = new Database(context);

        String tenhoatdong = ctct.getTenHoatDong();
        String ngay = ctct.getNgay();
        String noidung = ctct.getNoiDung();
        int tongtien = ctct.getTongTien();

        TextView lbTenHoatDongLichSu = convertView.findViewById(R.id.lbTenHoatDongLichSu);
        lbTenHoatDongLichSu.setText(tenhoatdong);

        TextView lbNgayLichSu = convertView.findViewById(R.id.lbNgayLichSu);
        lbNgayLichSu.setText(String.format("Ngày: %s", ngay));

        TextView lbNoiDungLichSu = convertView.findViewById(R.id.lbNoiDungLichSu);
        lbNoiDungLichSu.setText(String.format("Nội dung: %s", noidung));

        TextView lbTongTienLichSu = convertView.findViewById(R.id.lbTongTienLichSu);
        lbTongTienLichSu.setText(String.format("Tổng tiền: %s VND", tongtien));

        Button btnChiTietLichSuList = convertView.findViewById(R.id.btnChiTietLichSuList);

        btnChiTietLichSuList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietLichSu.class);
                intent.putExtra("tenhoatdong", tenhoatdong);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
