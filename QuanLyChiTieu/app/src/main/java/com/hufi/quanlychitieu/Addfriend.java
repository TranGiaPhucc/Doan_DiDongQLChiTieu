package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Addfriend extends AppCompatActivity {

    Button btnAddFriend2;
    EditText txtUsernameBan;
    TextView lbThongTinBan, lbSdtBan, lbKetBan;
    Database db;
    boolean checkThongTin = false;
    String username, myUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfiend);

        btnAddFriend2 = findViewById(R.id.btnAddFriend2);
        txtUsernameBan = findViewById(R.id.txtUsernameBan);
        lbThongTinBan = findViewById(R.id.lbThongTinBan);
        lbSdtBan = findViewById(R.id.lbSdtBan);
        lbKetBan = findViewById(R.id.lbKetBan);

        db = new Database(Addfriend.this);
        db.createTable();

        myUsername = getIntent().getStringExtra("username");

        txtUsernameBan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lbThongTinBan.setText("Chưa nhập username.");
                lbSdtBan.setText("");
                checkThongTin = false;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                username = txtUsernameBan.getText().toString();

                boolean isExist = db.checkUserNameExist(username);

                if (username.equals("")) {
                    lbThongTinBan.setText("Chưa nhập username.");
                    lbSdtBan.setText("");
                    lbKetBan.setText("");
                    checkThongTin = false;
                }
                else if (username.equals(myUsername)) {
                    lbThongTinBan.setText("Không thể thêm bản thân bạn được.");
                    lbSdtBan.setText("");
                    lbKetBan.setText("");
                    checkThongTin = false;
                }
                else if (isExist) {
                    NguoiDung nd = db.getNguoiDung(username);
                    String name = nd.getName();
                    String sdt = nd.getSdt();
                    lbThongTinBan.setText("Tên: " + name);
                    lbSdtBan.setText("SĐT: " + sdt);
                    if (!db.checkFriendExist(myUsername, username))
                    {
                        lbKetBan.setText("Chưa kết bạn.");
                        checkThongTin = true;
                    }
                    else
                    {
                        lbKetBan.setText("Đã kết bạn.");
                        checkThongTin = false;
                    }
                }
                else {
                    lbThongTinBan.setText("Username không tồn tại.");
                    lbSdtBan.setText("");
                    lbKetBan.setText("");
                    checkThongTin = false;
                }
            }
        });

        btnAddFriend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // xu ly du lieu
                if (checkThongTin)
                {
                    BanBe bb = new BanBe(myUsername, username, 0, 0);
                    db.insertBanBe(bb);
                    BanBe bb1 = new BanBe(username, myUsername, 0, 0);
                    db.insertBanBe(bb1);
                    lbKetBan.setText("Đã kết bạn.");
                    Toast.makeText(Addfriend.this, "Đã kết bạn với " + username + ".", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(Addfriend.this, "Username không hợp lệ.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}