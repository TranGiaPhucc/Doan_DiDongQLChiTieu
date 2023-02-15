package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DanhSachBanBe extends AppCompatActivity {
    Button btnReturn;
    ListView listFriend;
    TextView lbMyUsernameInvisible;

    public static String myUsernameAdapter = "";
    //public static AdapterDanhSachBanBe adapterDSBBstatic;

    ArrayList<NguoiDung> arrayList;
    //ArrayAdapter arrayAdapter;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_ban_be);

        db = new Database(DanhSachBanBe.this);
        db.createTable();

        String myUsername = getIntent().getStringExtra("username");
        myUsernameAdapter = myUsername;

        listFriend = findViewById(R.id.listFriend);

        arrayList =new ArrayList<>();
        /*
        arrayAdapter =new ArrayAdapter(DanhSachBanBe.this,
                android.R.layout.simple_list_item_1,
                arrayList);
        arrayAdapter =new ArrayAdapter(DanhSachBanBe.this,
                R.layout.list_danhsachbanbe,
                arrayList);
        arrayAdapter.clear();
        arrayList.addAll(db.getBanBeAll(myUsername));
        arrayAdapter.notifyDataSetChanged();
        */

        AdapterDanhSachBanBe adapterDanhSachBanBe = new AdapterDanhSachBanBe(DanhSachBanBe.this, R.layout.list_danhsachbanbe, arrayList);
        listFriend.setAdapter(adapterDanhSachBanBe);
        //adapterDSBBstatic = adapterDanhSachBanBe;

        adapterDanhSachBanBe.clear();
        arrayList.addAll(db.getBanBeDanhSachAll(myUsername));
        adapterDanhSachBanBe.notifyDataSetChanged();

        btnReturn = findViewById(R.id.btnReturn);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}