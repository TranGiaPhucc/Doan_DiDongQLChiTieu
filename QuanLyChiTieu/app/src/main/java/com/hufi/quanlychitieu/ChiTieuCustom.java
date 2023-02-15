package com.hufi.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ChiTieuCustom extends AppCompatActivity {
    Button btnNext;
    EditText txtTenHoatDong, txtNgay, txtSoTien, txtNoiDung;
    Database db;

    final Calendar myCalendar= Calendar.getInstance();

    public static String myUsernameAdapter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tieu_custom);

        db = new Database(ChiTieuCustom.this);

        btnNext=findViewById(R.id.btnNext);
        txtTenHoatDong=findViewById(R.id.txtTenHoatDong);
        txtNgay=findViewById(R.id.txtNgay);
        txtSoTien=findViewById(R.id.txtSoTien);
        txtNoiDung=findViewById(R.id.txtNoiDung);

        String myUsername = getIntent().getStringExtra("username");
        myUsernameAdapter = myUsername;

        String name = getIntent().getStringExtra("name");

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        txtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ChiTieuCustom.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTieuCustom.this, ChiTieuCustom2.class);

                String TenHD = txtTenHoatDong.getText().toString();
                String Ngay = txtNgay.getText().toString();
                String SoTien = txtSoTien.getText().toString();
                String NoiDung = txtNoiDung.getText().toString();

                if (TenHD.equals("") == true || Ngay.equals("") == true || SoTien.equals("") == true || NoiDung.equals("") == true) {
                    Toast.makeText(ChiTieuCustom.this, "Chưa nhập đủ thông tin.", Toast.LENGTH_SHORT).show();
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
                    else {
                        Toast.makeText(ChiTieuCustom.this, "Tên hoạt động bị trùng với hoạt động cũ.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        txtNgay.setText(dateFormat.format(myCalendar.getTime()));
    }
}