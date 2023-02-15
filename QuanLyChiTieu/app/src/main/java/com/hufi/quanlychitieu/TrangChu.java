package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TrangChu extends AppCompatActivity {
    ImageButton btnTrungBinh, btnCustom, btnAddFriend, btnFriend, btnThongTin;
    Button btnLogout, btnReload;
    TextView lbTen, lbSdt;
    ListView listLichSu;
    ArrayList<ChiTietChiTieu> arrayList;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        db = new Database(TrangChu.this);
        db.createTable();

        lbTen=findViewById(R.id.lbTen);
        lbSdt=findViewById(R.id.lbSdt);

        btnThongTin=findViewById(R.id.btnThongTin);
        btnTrungBinh=findViewById(R.id.btnTrungBinh);
        btnFriend=findViewById(R.id.btnFriend);
        btnAddFriend=findViewById(R.id.btnAddFriend);
        btnCustom=findViewById(R.id.btnCustom);
        btnLogout=findViewById(R.id.btnLogout);
        btnReload=findViewById(R.id.btnReload);

        listLichSu=findViewById(R.id.lstLichSu);

        String username = getIntent().getStringExtra("username");

        NguoiDung nguoiDung = db.getNguoiDung(username);

        String name = nguoiDung.getName();
        String sdt = nguoiDung.getSdt();
        lbTen.setText(name);
        lbSdt.setText(sdt);

        arrayList =new ArrayList<>();
        AdapterLichSu adapterLichSu = new AdapterLichSu(TrangChu.this, R.layout.list_lichsu, arrayList);
        listLichSu.setAdapter(adapterLichSu);

        adapterLichSu.clear();
        arrayList.addAll(db.getChiTietChiTieuAll(username));
        adapterLichSu.notifyDataSetChanged();

        btnThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChu.this, ThongTinCaNhan.class);
                intent.putExtra("username", username);
                intent.putExtra("name", name);
                intent.putExtra("sdt", sdt);
                intent.putExtra("password", nguoiDung.getPassword());
                startActivity(intent);
            }
        });

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reload();
            }
        });

        btnTrungBinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChu.this, ChiTieuTrungBinh.class);
                intent.putExtra("username", username);
                intent.putExtra("name", name);
                intent.putExtra("sdt", sdt);
                startActivity(intent);
            }
        });

        btnFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChu.this, DanhSachBanBe.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChu.this, Addfriend.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChu.this, ChiTieuCustom.class);
                intent.putExtra("username", username);
                intent.putExtra("name", name);
                intent.putExtra("sdt", sdt);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TrangChu.this, "Đăng xuất.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    public void reload()
    {
        finish();
        startActivity(getIntent());
    }
}