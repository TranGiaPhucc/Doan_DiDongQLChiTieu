package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChiTieuCustom2 extends AppCompatActivity {
    Button btnNext2;
    TextView lbTenHoatDong2, lbNgay2, lbSoTien2, lbNoiDung2;
    Database db;
    ListView listCustom2;
    ArrayList<NguoiDung> arrayList;

    public static String myUsernameAdapter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tieu_custom2);

        btnNext2=findViewById(R.id.btnNext2);

        db = new Database(ChiTieuCustom2.this);
        db.createTable();

        lbTenHoatDong2=findViewById(R.id.lbTenHoatDong);
        lbNgay2=findViewById(R.id.lbNgay2);
        lbSoTien2=findViewById(R.id.lbSoTien2);
        lbNoiDung2=findViewById(R.id.lbNoiDung2);

        lbTenHoatDong2.setText(getIntent().getStringExtra("TenHD"));
        lbNgay2.setText(getIntent().getStringExtra("Ngay"));
        lbSoTien2.setText(getIntent().getStringExtra("SoTien"));
        lbNoiDung2.setText(getIntent().getStringExtra("NoiDung"));

        String myUsername = getIntent().getStringExtra("username");
        myUsernameAdapter = myUsername;

        String name = getIntent().getStringExtra("name");

        listCustom2 = findViewById(R.id.listCustom2);

        arrayList =new ArrayList<>();
        AdapterChiTieuCustom2 adapterChiTieuCustom2 = new AdapterChiTieuCustom2(ChiTieuCustom2.this, R.layout.list_chitieucustom2, arrayList);
        listCustom2.setAdapter(adapterChiTieuCustom2);

        adapterChiTieuCustom2.clear();
        arrayList.addAll(db.getBanBeAll(myUsername));
        adapterChiTieuCustom2.notifyDataSetChanged();

        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTieuCustom2.this, ChiTieuCustom3.class);

                intent.putExtra("username", myUsername);
                intent.putExtra("name", name);

                intent.putExtra("TenHD", lbTenHoatDong2.getText().toString());
                intent.putExtra("Ngay", lbNgay2.getText().toString());
                intent.putExtra("SoTien", lbSoTien2.getText().toString());
                intent.putExtra("NoiDung", lbNoiDung2.getText().toString());

                startActivity(intent);

                finish();
            }
        });
    }
}