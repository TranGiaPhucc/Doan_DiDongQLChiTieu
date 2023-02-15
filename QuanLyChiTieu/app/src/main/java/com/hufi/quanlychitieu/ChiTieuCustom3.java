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

public class ChiTieuCustom3 extends AppCompatActivity {
    Button btnLuuCustom;
    TextView lbTenHoatDong3, lbNgay3, lbSoTien3, lbNoiDung3;
    Database db;
    ListView listCustom3;
    ArrayList<NguoiDung> arrayList;

    public static String myUsernameAdapter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tieu_custom3);

        btnLuuCustom=findViewById(R.id.btnLuuCustom);

        String name = getIntent().getStringExtra("name");
        String sdt = getIntent().getStringExtra("sdt");

        String myUsername = getIntent().getStringExtra("username");
        myUsernameAdapter = myUsername;

        db = new Database(ChiTieuCustom3.this);
        db.createTable();

        lbTenHoatDong3=findViewById(R.id.lbTenHoatDong);
        lbNgay3=findViewById(R.id.lbNgay3);
        lbSoTien3=findViewById(R.id.lbSoTien3);
        lbNoiDung3=findViewById(R.id.lbNoiDung3);

        lbTenHoatDong3.setText(getIntent().getStringExtra("TenHD"));
        lbNgay3.setText(getIntent().getStringExtra("Ngay"));
        lbSoTien3.setText(getIntent().getStringExtra("SoTien"));
        lbNoiDung3.setText(getIntent().getStringExtra("NoiDung"));

        String tenhoatdong = lbTenHoatDong3.getText().toString();
        String ngay = lbNgay3.getText().toString();
        String noidung = lbNoiDung3.getText().toString();
        int tongTien = Integer.parseInt(lbSoTien3.getText().toString());

        listCustom3 = findViewById(R.id.listCustom3);

        arrayList =new ArrayList<>();
        AdapterChiTieuCustom3 adapterChiTieuCustom3 = new AdapterChiTieuCustom3(ChiTieuCustom3.this, R.layout.list_chitieucustom3, arrayList);
        listCustom3.setAdapter(adapterChiTieuCustom3);

        adapterChiTieuCustom3.clear();
        arrayList.addAll(db.getBanBeCheckCustom2All(myUsername));
        adapterChiTieuCustom3.notifyDataSetChanged();

        btnLuuCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sum = 0;
                int itemsCount = listCustom3.getChildCount();
                for(int i=0; i<itemsCount; i++) {
                    view = listCustom3.getChildAt(i);
                    EditText txtTienBanList = view.findViewById(R.id.txtTienBanList);
                    String text = txtTienBanList.getText().toString();
                    int value = 0;
                    if (text.equals(""))
                        value = 0;
                    else
                        value = Integer.parseInt(text);
                    sum = sum + value;
                }
                if (tongTien != sum)
                    Toast.makeText(ChiTieuCustom3.this, "Tổng tiền cộng lại khác với tổng tiền từ hóa đơn.", Toast.LENGTH_SHORT).show();
                else {
                    boolean isExist = db.checkChiTietChiTieuExist(tenhoatdong);
                    if (!isExist) {
                        ChiTietChiTieu ctct = new ChiTietChiTieu(tenhoatdong, ngay, noidung, tongTien);
                        db.insertChiTietChiTieu(ctct);
                        for (int i=0; i<itemsCount; i++) {
                            view = listCustom3.getChildAt(i);
                            TextView lbUsernameBanInvisibleList = view.findViewById(R.id.lbUsernameBanInvisibleList);
                            EditText txtTienBanList = view.findViewById(R.id.txtTienBanList);
                            String usernameBan = lbUsernameBanInvisibleList.getText().toString();
                            String text = txtTienBanList.getText().toString();
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
                        Toast.makeText(ChiTieuCustom3.this, "Tên hoạt động bị trùng với hoạt động cũ.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}