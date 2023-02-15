package com.hufi.quanlychitieu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;
import org.xml.sax.helpers.XMLFilterImpl;

import java.util.ArrayList;

public class AdapterDanhSachBanBe extends ArrayAdapter<NguoiDung> {
    Context context;
    int layoutResource;
    ArrayList<NguoiDung> data;
    Database db;


    public AdapterDanhSachBanBe(@NonNull Context context, int resource, @NonNull ArrayList<NguoiDung> objects) {
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

        String username = nd.getUsername();
        String name = nd.getName();
        String sdt = nd.getSdt();

        String myUsername = DanhSachBanBe.myUsernameAdapter;

        TextView lbTen = convertView.findViewById(R.id.lbTenBanList);
        lbTen.setText(name);

        TextView lbUsername = convertView.findViewById(R.id.lbUsernameBanList);
        lbUsername.setText(String.format("Username: %s", username));

        TextView lbSdt = convertView.findViewById(R.id.lbSdtBanList);
        lbSdt.setText(String.format("Sdt: %s", sdt));

        Button btnXoa = convertView.findViewById(R.id.btnXoaBanList);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, XoaBan.class);
                intent.putExtra("name", name);
                intent.putExtra("username", username);
                intent.putExtra("myUsername", myUsername);
                context.startActivity(intent);

                //DanhSachBanBe.adapterDSBBstatic.clear();
                //DanhSachBanBe.adapterDSBBstatic.notifyDataSetChanged();;

                /*
                BanBe bb = new BanBe(, nd.getUsername());
                db.deleteBanBe(bb);
                BanBe bb1 = new BanBe(nd.getUsername(), );
                db.deleteBanBe(bb1);
                Toast.makeText(context, "Đã xóa bạn " + lbUsername.getText().toString() + " (username: " + lbTen.getText().toString() + ").",Toast.LENGTH_SHORT).show();
                 */
            }
        });

        return convertView;
    }
}
