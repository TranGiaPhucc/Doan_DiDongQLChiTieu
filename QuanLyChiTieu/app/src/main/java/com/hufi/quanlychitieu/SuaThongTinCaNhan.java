package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SuaThongTinCaNhan extends AppCompatActivity {
    Button btnSuaTTCN;
    EditText txtTenSua, txtSdtSua;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thong_tin_ca_nhan);

        db = new Database(SuaThongTinCaNhan.this);

        String username = getIntent().getStringExtra("username");

        txtTenSua = findViewById(R.id.txtTenSua);
        txtSdtSua = findViewById(R.id.txtSdtSua);

        btnSuaTTCN = findViewById(R.id.btnSuaTTCN);

        btnSuaTTCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = txtTenSua.getText().toString();
                String sdt = txtSdtSua.getText().toString();

                if (ten.equals("") || sdt.equals(""))
                    Toast.makeText(SuaThongTinCaNhan.this, "Chưa nhập đủ thông tin.", Toast.LENGTH_SHORT).show();
                else {
                    NguoiDung nd = db.getNguoiDung(username);
                    nd.setName(ten);
                    nd.setSdt(sdt);
                    db.update(nd);
                    finish();
                }
            }
        });
    }
}