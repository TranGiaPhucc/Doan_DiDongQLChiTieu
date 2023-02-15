package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button btnLogin, btnRegister;
    EditText txtUsername, txtPassword;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(Login.this);
        db.createTable();

        //Người dùng mẫu
        if (!db.checkUserExist("maitruong", "maitruong")) {
            NguoiDung nd = new NguoiDung("maitruong", "maitruong", "Mai Trường", "013445678");
            db.insert(nd);
            BanBe bb = new BanBe("maitruong", "maitruong", 0,0);
            db.insertBanBe(bb);
        }
        if (!db.checkUserExist("tramy", "tramy")) {
            NguoiDung nd = new NguoiDung("tramy", "tramy", "Trà My", "01223345678");
            db.insert(nd);
            BanBe bb = new BanBe("tramy", "tramy", 0,0);
            db.insertBanBe(bb);
        }
        if (!db.checkUserExist("admin", "admin")) {
            NguoiDung nd = new NguoiDung("admin", "admin", "Trần Gia Phúc", "012345678");
            db.insert(nd);
            BanBe bb = new BanBe("admin", "admin", 0,0);
            db.insertBanBe(bb);
        }
        if (!db.checkUserExist("ad", "ad")) {
            NguoiDung nd = new NguoiDung("ad", "ad", "asd", "012345");
            db.insert(nd);
            BanBe bb = new BanBe("ad", "ad", 0,0);
            db.insertBanBe(bb);
        }
        if (!db.checkUserExist("adm", "adm")) {
            NguoiDung nd = new NguoiDung("adm", "adm", "ADM", "011271932");
            db.insert(nd);
            BanBe bb = new BanBe("adm", "adm", 0,0);
            db.insertBanBe(bb);
        }

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                boolean isExist = db.checkUserExist(username, password);

                if (username.equals("") == true || password.equals("") == true) {
                    Toast.makeText(Login.this, "Chưa nhập username/password.", Toast.LENGTH_SHORT).show();
                }
                else if (isExist) {
                    Intent intent = new Intent(Login.this, TrangChu.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
                else
                    Toast.makeText(Login.this, "Sai username/password.", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        
        
    }
}