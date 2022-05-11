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
                /*else if (db.getNguoiDung(username) == {"username", "password"})
                {

                }
                */
                //else if (username.equals("admin") == true && password.equals("admin") == true){
                else if (isExist) {
                    Intent intent = new Intent(Login.this, TrangChu.class);
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