package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class XoaBan extends AppCompatActivity {
    Button btnDongYXoa, btnHuyXoa;
    TextView lbXoaBanList;
    Database db = new Database(XoaBan.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xoa_ban);

        btnDongYXoa = findViewById(R.id.btnDongYXoa);
        btnHuyXoa = findViewById(R.id.btnHuyXoa);

        String name = getIntent().getStringExtra("name");
        String username = getIntent().getStringExtra("username");
        String myUsername = getIntent().getStringExtra("myUsername");

        lbXoaBanList = findViewById(R.id.lbXoaBanList);
        lbXoaBanList.setText("Bạn có đồng ý xóa bạn " + name + " (username: " + username + ") không?");

        btnDongYXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BanBe bb = new BanBe(myUsername, username);
                db.deleteBanBe(bb);
                BanBe bb1 = new BanBe(username, myUsername);
                db.deleteBanBe(bb1);
                Toast.makeText(XoaBan.this, "Đã xóa bạn " + name + " (username: " + username + ").",Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        btnHuyXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}