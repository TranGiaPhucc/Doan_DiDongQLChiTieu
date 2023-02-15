package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoiMatKhau extends AppCompatActivity {
    Button btnDoiMK;
    EditText txtMatKhauCu, txtMatKhauMoi, txtXacNhanMatKhauMoi;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        db = new Database(DoiMatKhau.this);

        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");

        btnDoiMK = findViewById(R.id.btnDoiMK);

        txtMatKhauCu = findViewById(R.id.txtMatKhauCu);
        txtMatKhauMoi = findViewById(R.id.txtMatKhauMoi);
        txtXacNhanMatKhauMoi = findViewById(R.id.txtXacNhanMatKhauMoi);

        btnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPass = txtMatKhauCu.getText().toString();
                String newPass = txtMatKhauMoi.getText().toString();
                String confirmPass = txtXacNhanMatKhauMoi.getText().toString();

                if (oldPass.equals("") || newPass.equals("") || confirmPass.equals(""))
                    Toast.makeText(DoiMatKhau.this, "Chưa nhập đủ thông tin.", Toast.LENGTH_SHORT).show();
                else if (!oldPass.equals(password)){
                    Toast.makeText(DoiMatKhau.this, "Nhập sai mật khẩu cũ.", Toast.LENGTH_SHORT).show();
                    txtMatKhauCu.setText("");
                    txtMatKhauMoi.setText("");
                    txtXacNhanMatKhauMoi.setText("");
                }
                else if (!newPass.equals(confirmPass)) {
                    Toast.makeText(DoiMatKhau.this, "Xác nhận mật khẩu không trùng khớp.", Toast.LENGTH_SHORT).show();
                    txtMatKhauMoi.setText("");
                    txtXacNhanMatKhauMoi.setText("");
                }
                else {
                    NguoiDung nd = db.getNguoiDung(username);
                    nd.setPassword(newPass);
                    db.update(nd);
                    finish();
                }
            }
        });
    }
}