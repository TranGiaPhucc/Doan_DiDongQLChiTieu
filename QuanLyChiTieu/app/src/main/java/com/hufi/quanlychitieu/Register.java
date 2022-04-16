package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    Button btnRegAccount;
    EditText txtUsernameReg, txtPasswordReg;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtUsernameReg = findViewById(R.id.txtUsernameReg);
        txtPasswordReg = findViewById(R.id.txtPasswordReg);
        btnRegAccount = findViewById(R.id.btnRegAccount);

        btnRegAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsernameReg.getText().toString();
                String password = txtPasswordReg.getText().toString();

                if (username.equals("") == true || password.equals("") == true)
                {
                    Toast.makeText(Register.this, "Chưa nhập username/password.", Toast.LENGTH_SHORT).show();
                }
                else {

                    NguoiDung nd = new NguoiDung(username, password);

                    if (db.insert(nd)) {
                        Toast.makeText(Register.this, "OK", Toast.LENGTH_SHORT).show();

                        //Intent intent = new Intent(Register.this, Login.class);
                        finish();
                    } else {
                        Toast.makeText(Register.this, "NOK", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}