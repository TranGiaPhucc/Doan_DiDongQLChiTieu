package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TrangChu extends AppCompatActivity {
    ImageButton btnAdd;
    Button btnAddFriend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        btnAdd=findViewById(R.id.btnAdd);
        btnAddFriend=findViewById(R.id.btnAddFriend);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChu.this, ChiTiet.class);
                startActivity(intent);
            }
        });

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChu.this, Addfiend.class);
                startActivity(intent);
            }
        });
    }
}