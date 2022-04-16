package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Addfiend extends AppCompatActivity {

    Button btnAddFriend2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfiend);

        btnAddFriend2 = findViewById(R.id.btnAddFriend2);

        btnAddFriend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // xu ly du lieu

                finish();
            }
        });
    }
}