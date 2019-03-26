package com.example.johnluu.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.johnluu.duan1.R;
import com.example.johnluu.duan1.database.TacGiaDAO;
import com.example.johnluu.duan1.model.TacGia;



import java.util.ArrayList;

public class TacGiaAdapter extends BaseAdapter {
    TacGia tg;
    TacGiaDAO tgdao;
    Context c;
    ArrayList<TacGia> dstg = new ArrayList<TacGia>();
    ListView lv;
    public TacGiaAdapter(Context c, ArrayList<TacGia> dstg,ListView lv){
        this.c=c;
        this.dstg=dstg;
        this.lv=lv;
    }
    @Override
    public int getCount() {
        return dstg.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inf = ((Activity)c).getLayoutInflater();
        view = inf.inflate(R.layout.one_item_tacgia,null);
        TextView tv_idtacgia_one_item = view.findViewById(R.id.tv_idtacgia_one_item);
        TextView tv_tentacgia_one_item = view.findViewById(R.id.tv_tentacgia_one_item);
        ImageView iv_option_one_item_tacgia = view.findViewById(R.id.iv_option_one_item_tacgia);

        TacGia tg = dstg.get(i);
        tv_idtacgia_one_item.setText(tg._idtacgia+"");
        tv_tentacgia_one_item.setText(tg.tentacgia);

        iv_option_one_item_tacgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMultiPopup(view,i);
            }
        });

        return view;
    }

    public void showMultiPopup(View view, final int position) {
        android.support.v7.widget.PopupMenu popup = new android.support.v7.widget.PopupMenu(c, view);
        popup.inflate(R.menu.del_edit_function);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.xoa_item:
                        final AlertDialog.Builder dialog = new AlertDialog.Builder(c);
                        dialog.setTitle("Thông báo");
                        dialog.setMessage("Bạn có muốn xóa tác giả này không?");
                        dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                TacGia tg = dstg.get(position);
                                int _id = tg._idtacgia;

                                TacGiaDAO tgdao = new TacGiaDAO(c);
                                tgdao.xoaTacGia(_id);
                                capnhatgiaodien_tacgia();
                            }
                        });
                        dialog.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        });
                        dialog.show();
                        break;
                    case R.id.sua_item:
                        final AlertDialog.Builder builder = new AlertDialog.Builder(c);
                        LayoutInflater inf = ((Activity)c).getLayoutInflater();
                        final View view= inf.inflate(R.layout.dialog_suatg,null);
                        builder.setView(view);

                        final AlertDialog a = builder.create();

                        final TacGia[] tg = {dstg.get(position)};
                        final TextView tv_id = view.findViewById(R.id.tv_id_suaTacGiadialog);
                        final EditText et_tentg = view.findViewById(R.id.et_tentacgia_suaTacGiadialog);
                        Button bt_sua = view.findViewById(R.id.bt_sua_tg);
                        Button bt_huy = view.findViewById(R.id.bthuy);

                        tv_id.setText(tg[0]._idtacgia+"");
                        et_tentg.setText(tg[0].tentacgia);

                        bt_sua.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int _id = Integer.parseInt(tv_id.getText().toString());
                                String tentg = et_tentg.getText().toString();

                                try{
                                    if(tentg.length()==0){
                                        Toast.makeText(c, "Tên Tác Giả không được rỗng!", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){};



                                TacGiaDAO tgdao = new TacGiaDAO(c);
                                dstg = tgdao.xemTacGia();

                                TacGia tgupdate = new TacGia(_id,tentg);
                                tgdao.suaTacgia(tgupdate);
                                capnhatgiaodien_tacgia();

                                a.cancel();
                            }
                        });

                        bt_huy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                a.cancel();
                            }
                        });


                        a.show();

                    default:

                        return true;
                }
                return false;
            }

        });
        popup.show();
    }

    public void capnhatgiaodien_tacgia(){
        TacGiaDAO tgdao = new TacGiaDAO(c);
        dstg = tgdao.xemTacGia();
        TacGiaAdapter tacgiaAdapter = new TacGiaAdapter(c,dstg,lv);
        lv.setAdapter(tacgiaAdapter);
    }


}