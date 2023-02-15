package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ChiTieuTrungBinh extends AppCompatActivity {
    Button btnNextTB;
    EditText txtTenHoatDongTB, txtNgayTB, txtSoTienTB, txtNoiDungTB;
    Database db;
    ListView listTB;
    ArrayList<NguoiDung> arrayList;

    final Calendar myCalendar= Calendar.getInstance();

    public static String myUsernameAdapter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tieu_trung_binh);

        btnNextTB=findViewById(R.id.btnNextTB);

        db = new Database(ChiTieuTrungBinh.this);
        db.createTable();

        txtTenHoatDongTB=findViewById(R.id.txtTenHoatDongTB);
        txtNgayTB=findViewById(R.id.txtNgayTB);
        txtSoTienTB=findViewById(R.id.txtSoTienTB);
        txtNoiDungTB=findViewById(R.id.txtNoiDungTB);

        String myUsername = getIntent().getStringExtra("username");
        myUsernameAdapter = myUsername;

        String name = getIntent().getStringExtra("name");

        listTB = findViewById(R.id.listTB);

        arrayList =new ArrayList<>();
        AdapterChiTieuTrungBinh adapterChiTieuTrungBinh = new AdapterChiTieuTrungBinh(ChiTieuTrungBinh.this, R.layout.list_chitieucustom2, arrayList);
        listTB.setAdapter(adapterChiTieuTrungBinh);

        adapterChiTieuTrungBinh.clear();
        arrayList.addAll(db.getBanBeAll(myUsername));
        adapterChiTieuTrungBinh.notifyDataSetChanged();

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        txtNgayTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ChiTieuTrungBinh.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnNextTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTieuTrungBinh.this, ChiTieuTrungBinh2.class);

                String TenHD = txtTenHoatDongTB.getText().toString();
                String Ngay = txtNgayTB.getText().toString();
                String SoTien = txtSoTienTB.getText().toString();
                String NoiDung = txtNoiDungTB.getText().toString();

                if (TenHD.equals("") == true || Ngay.equals("") == true || SoTien.equals("") == true || NoiDung.equals("") == true) {
                    Toast.makeText(ChiTieuTrungBinh.this, "Chưa nhập đủ thông tin.", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean isExist = db.checkChiTietChiTieuExist(TenHD);
                    if (!isExist) {
                        intent.putExtra("username", myUsername);
                        intent.putExtra("name", name);

                        intent.putExtra("TenHD", TenHD);
                        intent.putExtra("Ngay", Ngay);
                        intent.putExtra("SoTien", SoTien);
                        intent.putExtra("NoiDung", NoiDung);

                        startActivity(intent);

                        finish();
                    }
                    else
                        Toast.makeText(ChiTieuTrungBinh.this, "Tên hoạt động bị trùng với hoạt động cũ.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        txtNgayTB.setText(dateFormat.format(myCalendar.getTime()));
    }
}