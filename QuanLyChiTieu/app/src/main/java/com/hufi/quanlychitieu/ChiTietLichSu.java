package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ChiTietLichSu extends AppCompatActivity {
    Button btnQuayLai;
    Database db;
    ListView listChiTietLichSu;
    ArrayList<ChiTietNguoiChiTieu> arrayList;

    public static String tenHoatDongAdapter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_lich_su);

        db = new Database(ChiTietLichSu.this);
        db.createTable();

        String tenhoatdong = getIntent().getStringExtra("tenhoatdong");
        tenHoatDongAdapter = tenhoatdong;

        listChiTietLichSu=findViewById(R.id.listChiTietLichSu);

        arrayList =new ArrayList<>();
        AdapterChiTietLichSu adapterChiTietLichSu = new AdapterChiTietLichSu(ChiTietLichSu.this, R.layout.list_chitietlichsu, arrayList);
        listChiTietLichSu.setAdapter(adapterChiTietLichSu);

        adapterChiTietLichSu.clear();
        arrayList.addAll(db.getChiTietNguoiChiTieuAll(tenhoatdong));
        adapterChiTietLichSu.notifyDataSetChanged();

        btnQuayLai = findViewById(R.id.btnQuayLaiLichSu);

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}