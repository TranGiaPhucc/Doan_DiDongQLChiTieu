package com.hufi.quanlychitieu;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterChiTieuTrungBinh2 extends ArrayAdapter<NguoiDung> {
    Context context;
    int layoutResource;
    ArrayList<NguoiDung> data;
    Database db;

    String value = "";

    public AdapterChiTieuTrungBinh2(@NonNull Context context, int resource, @NonNull ArrayList<NguoiDung> objects) {
        super(context, resource, objects);
        this.data=objects;
        this.layoutResource=resource;
        this.context=context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public NguoiDung getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(layoutResource, parent, false);

        NguoiDung nd = data.get(position);

        db = new Database(context);

        String myUsername = ChiTieuTrungBinh2.myUsernameAdapter;

        String lbTien = ChiTieuTrungBinh2.lbTienTrungBinh;
        int tien = Integer.parseInt(lbTien)/getCount();
        String tienStr = String.valueOf(tien);

        String username = nd.getUsername();
        String name = nd.getName();
        String sdt = nd.getSdt();

        TextView lbUsernameInvisible = convertView.findViewById(R.id.lbUsernameBanInvisibleTBList);
        lbUsernameInvisible.setText(username);

        TextView lbTienInvisible = convertView.findViewById(R.id.lbTienBanInvisibleTBList);
        lbTienInvisible.setText(tienStr);

        TextView lbTen = convertView.findViewById(R.id.lbTenBanList);
        lbTen.setText(name);

        TextView lbUsername = convertView.findViewById(R.id.lbUsernameBanList);
        lbUsername.setText(String.format("Username: %s", username));

        TextView lbSdt = convertView.findViewById(R.id.lbSdtBanList);
        lbSdt.setText(String.format("Sdt: %s", sdt));

        TextView lbTienBanList = convertView.findViewById(R.id.lbTienBanList);
        lbTienBanList.setText(String.format("%s", tien));

        return convertView;
    }
}
