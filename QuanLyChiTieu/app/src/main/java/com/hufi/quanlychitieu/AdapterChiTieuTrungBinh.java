package com.hufi.quanlychitieu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdapterChiTieuTrungBinh extends ArrayAdapter<NguoiDung> {
    Context context;
    int layoutResource;
    ArrayList<NguoiDung> data;
    Database db;


    public AdapterChiTieuTrungBinh(@NonNull Context context, int resource, @NonNull ArrayList<NguoiDung> objects) {
        super(context, resource, objects);
        this.data=objects;
        this.layoutResource=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(layoutResource, parent, false);

        NguoiDung nd = data.get(position);

        db = new Database(context);

        String myUsername = ChiTieuTrungBinh.myUsernameAdapter;

        String username = nd.getUsername();
        String name = nd.getName();
        String sdt = nd.getSdt();

        BanBe b = new BanBe(myUsername, username);
        BanBe bb = db.getBanBe(b);

        TextView lbTen = convertView.findViewById(R.id.lbTenBanList);
        lbTen.setText(name);

        TextView lbUsername = convertView.findViewById(R.id.lbUsernameBanList);
        lbUsername.setText(String.format("Username: %s", username));

        TextView lbSdt = convertView.findViewById(R.id.lbSdtBanList);
        lbSdt.setText(String.format("Sdt: %s", sdt));

        CheckBox checkChonBanList = convertView.findViewById(R.id.checkChonBanList);
        checkChonBanList.setTag(position);
        //checkChonBanList.setChecked(false);

        if (bb.getCheckChiTietCustom2() == 1) {
            checkChonBanList.setChecked(true);
        }
        else  {
            checkChonBanList.setChecked(false);
        }
/*
        if (myUsername.equals(username)) {
            checkChonBanList.setChecked(true);
            checkChonBanList.setEnabled(false);
        }
*/


        checkChonBanList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    bb.setCheckChiTietCustom2(1);
                    db.updateBanBe(bb);
                }else{
                    bb.setCheckChiTietCustom2(0);
                    db.updateBanBe(bb);
                }
            }
        });

        /*
        checkChonBanList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    bb.setCheckChiTietCustom2(1);
                    db.updateBanBe(bb);
                }
                else
                {
                    bb.setCheckChiTietCustom2(0);
                    db.updateBanBe(bb);
                }
            }
        });*/

        return convertView;
    }
}
