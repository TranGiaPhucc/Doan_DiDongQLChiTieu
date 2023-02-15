package com.hufi.quanlychitieu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdapterChiTietLichSu extends ArrayAdapter<ChiTietNguoiChiTieu> {
    Context context;
    int layoutResource;
    ArrayList<ChiTietNguoiChiTieu> data;
    Database db;

    public AdapterChiTietLichSu(@NonNull Context context, int resource, @NonNull ArrayList<ChiTietNguoiChiTieu> objects) {
        super(context, resource, objects);
        this.data=objects;
        this.layoutResource=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        ChiTietNguoiChiTieu ctnct = data.get(position);

        convertView = layoutInflater.inflate(layoutResource, parent, false);
        db = new Database(context);

        String tenhoatdong = ChiTietLichSu.tenHoatDongAdapter;

        String username = ctnct.getUsername();
        int tien = ctnct.getTien();

        NguoiDung nd = db.getNguoiDung(username);
        String ten = nd.getName();

        TextView lbTenChiTietLichSu = convertView.findViewById(R.id.lbTenChiTietLichSu);
        lbTenChiTietLichSu.setText(ten);

        TextView lbUsernameChiTietLichSu = convertView.findViewById(R.id.lbUsernameChiTietLichSu);
        lbUsernameChiTietLichSu.setText(String.format("Username: %s", username));

        TextView lbTienChiTietLichSu = convertView.findViewById(R.id.lbTienChiTietLichSu);
        lbTienChiTietLichSu.setText(String.format("Tiền: %s VND", tien));

        return convertView;
    }
}
