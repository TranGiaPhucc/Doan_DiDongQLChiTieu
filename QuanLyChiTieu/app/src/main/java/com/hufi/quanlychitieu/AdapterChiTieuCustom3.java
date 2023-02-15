package com.hufi.quanlychitieu;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterChiTieuCustom3 extends ArrayAdapter<NguoiDung> {
    Context context;
    int layoutResource;
    ArrayList<NguoiDung> data;
    Database db;

    String value = "";

    public AdapterChiTieuCustom3(@NonNull Context context, int resource, @NonNull ArrayList<NguoiDung> objects) {
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

        ViewHolder viewHolder = new ViewHolder();

        NguoiDung nd = data.get(position);
        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            db = new Database(context);

            String myUsername = ChiTieuCustom2.myUsernameAdapter;

            String username = nd.getUsername();
            String name = nd.getName();
            String sdt = nd.getSdt();

            viewHolder.lbTen = convertView.findViewById(R.id.lbTenBanList);
            viewHolder.lbTen.setText(name);

            viewHolder.lbUsernameInvisible = convertView.findViewById(R.id.lbUsernameBanInvisibleList);
            viewHolder.lbUsernameInvisible.setText(username);

            viewHolder.lbUsername = convertView.findViewById(R.id.lbUsernameBanList);
            viewHolder.lbUsername.setText(String.format("Username: %s", username));

            viewHolder.lbSdt = convertView.findViewById(R.id.lbSdtBanList);
            viewHolder.lbSdt.setText(String.format("Sdt: %s", sdt));

            viewHolder.txtTienBanList = convertView.findViewById(R.id.txtTienBanList);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
    private class ViewHolder {
        TextView lbTen;
        TextView lbUsername;
        TextView lbSdt;
        EditText txtTienBanList;
        TextView lbUsernameInvisible;
    }
}
