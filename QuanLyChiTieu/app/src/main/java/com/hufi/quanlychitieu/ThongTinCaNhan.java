package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThongTinCaNhan extends AppCompatActivity {
    Button btnSuaThongTin, btnDoiMatKhau, btnQuayLaiCaiDat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);

        String name = getIntent().getStringExtra("name");
        String sdt = getIntent().getStringExtra("sdt");
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");

        btnQuayLaiCaiDat = findViewById(R.id.btnQuayLaiCaiDat);
        btnSuaThongTin = findViewById(R.id.btnSuaThongTinCaNhan);
        btnDoiMatKhau = findViewById(R.id.btnDoiMatKhau);

        btnQuayLaiCaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSuaThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongTinCaNhan.this, SuaThongTinCaNhan.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            }
        });

        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongTinCaNhan.this, DoiMatKhau.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                startActivity(intent);
                finish();
            }
        });
    }
}