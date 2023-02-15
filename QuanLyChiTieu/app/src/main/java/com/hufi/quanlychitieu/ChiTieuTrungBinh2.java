package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChiTieuTrungBinh2 extends AppCompatActivity {
    Button btnLuuTB;
    TextView lbTenHoatDongTB, lbNgayTB, lbSoTienTB, lbNoiDungTB;
    Database db;
    ListView listTB2;
    ArrayList<NguoiDung> arrayList;

    public static String myUsernameAdapter = "";
    public static String lbTienTrungBinh = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tieu_trung_binh2);

        btnLuuTB=findViewById(R.id.btnLuuTB);

        String name = getIntent().getStringExtra("name");
        String sdt = getIntent().getStringExtra("sdt");

        String tenhoatdong = getIntent().getStringExtra("TenHD");
        String ngay = getIntent().getStringExtra("Ngay");
        String tien = getIntent().getStringExtra("SoTien");
        int tongTien = Integer.parseInt(tien);
        String noidung = getIntent().getStringExtra("NoiDung");

        String myUsername = getIntent().getStringExtra("username");
        myUsernameAdapter = myUsername;

        db = new Database(ChiTieuTrungBinh2.this);
        db.createTable();

        lbTenHoatDongTB=findViewById(R.id.lbTenHoatDongTB);
        lbNgayTB=findViewById(R.id.lbNgayTB);
        lbSoTienTB=findViewById(R.id.lbSoTienTB);
        lbNoiDungTB=findViewById(R.id.lbNoiDungTB);

        lbTenHoatDongTB.setText(tenhoatdong);
        lbNgayTB.setText(ngay);
        lbSoTienTB.setText(tien);
        lbNoiDungTB.setText(noidung);

        lbTienTrungBinh = lbSoTienTB.getText().toString();

        listTB2 = findViewById(R.id.listTB2);

        arrayList =new ArrayList<>();
        AdapterChiTieuTrungBinh2 adapterChiTieuTrungBinh2 = new AdapterChiTieuTrungBinh2(ChiTieuTrungBinh2.this, R.layout.list_chitieutrungbinh2, arrayList);
        listTB2.setAdapter(adapterChiTieuTrungBinh2);

        adapterChiTieuTrungBinh2.clear();
        arrayList.addAll(db.getBanBeCheckCustom2All(myUsername));
        adapterChiTieuTrungBinh2.notifyDataSetChanged();

        btnLuuTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemsCount = listTB2.getChildCount();
                boolean isExist = db.checkChiTietChiTieuExist(tenhoatdong);
                if (!isExist) {
                    ChiTietChiTieu ctct = new ChiTietChiTieu(tenhoatdong, ngay, noidung, tongTien);
                    db.insertChiTietChiTieu(ctct);
                    for (int i=0; i<itemsCount; i++) {
                        view = listTB2.getChildAt(i);
                        TextView lbUsernameBanInvisibleTBList = view.findViewById(R.id.lbUsernameBanInvisibleTBList);
                        TextView lbTienBanInvisibleTBList = view.findViewById(R.id.lbTienBanInvisibleTBList);
                        String usernameBan = lbUsernameBanInvisibleTBList.getText().toString();
                        String text = lbTienBanInvisibleTBList.getText().toString();
                        int value = Integer.parseInt(text);
                        ChiTietNguoiChiTieu ctnct = new ChiTietNguoiChiTieu(tenhoatdong, usernameBan, value);
                        db.insertChiTietNguoiChiTieu(ctnct);

                        BanBe b = new BanBe(myUsername, usernameBan);
                        BanBe bb = db.getBanBeTongTien(b);
                        BanBe b1 = new BanBe(usernameBan, myUsername);
                        BanBe bb1 = db.getBanBeTongTien(b1);
                        int congtien = bb.getTongTien() + value;
                        bb.setCheckChiTietCustom2(0);
                        bb.setTongTien(congtien);
                        db.updateBanBe(bb);
                        bb1.setCheckChiTietCustom2(0);
                        bb1.setTongTien(congtien);
                        db.updateBanBe(bb1);
                    }
                    finish();
                }
                else
                    Toast.makeText(ChiTieuTrungBinh2.this, "Tên hoạt động bị trùng với hoạt động cũ.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}