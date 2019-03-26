package com.example.johnluu.duan1;

import android.app.AlertDialog;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.johnluu.duan1.adapter.TacGiaAdapter;
import com.example.johnluu.duan1.database.TacGiaDAO;
import com.example.johnluu.duan1.model.TacGia;


import java.util.ArrayList;


public class Fragment_tacgia extends Fragment {
    TacGia tg;
    TacGiaDAO tgdao;
    TacGiaAdapter tacgiaAdapter;
    ListView lv_tacgia;
    ArrayList<TacGia> dstg = new ArrayList<TacGia>();
    public Fragment_tacgia() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Tác giả");
        View v = inflater.inflate(R.layout.fragment_tacgia, container, false);

        FloatingActionButton fab_themtacgia = v.findViewById(R.id.fab_add);
        lv_tacgia=v.findViewById(R.id.lv_tg);

        capnhatgiaodien_tacgia();

        fab_themtacgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inf = getLayoutInflater();
                final View vi = inf.inflate(R.layout.dialog_themtg,null);
                final EditText et_tentacgia = vi.findViewById(R.id.etadd);
                Button bt_them_tacgia = vi.findViewById(R.id.btthem);
                Button bt_huy = vi.findViewById(R.id.bthuy);

                builder.setView(vi);
                final AlertDialog a = builder.create();

                bt_them_tacgia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tentacgia = et_tentacgia.getText().toString();

                        if(tentacgia.isEmpty()){
                            Toast.makeText(getContext(), "Tên Tác Giả không được rỗng!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            tg = new TacGia(tentacgia);
                            tgdao = new TacGiaDAO(getContext());
                            tgdao.ThemTacGia(tg);
                            capnhatgiaodien_tacgia();
                            a.dismiss();
                        }
                    }
                });

                bt_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a.dismiss();

                    }
                });

                a.show();
            }
        });

        return v;
    }



    public void capnhatgiaodien_tacgia(){
        tgdao = new TacGiaDAO(getActivity());
        dstg = tgdao.xemTacGia();
        tacgiaAdapter = new TacGiaAdapter(getActivity(),dstg,lv_tacgia);
        lv_tacgia.setAdapter(tacgiaAdapter);
    }
}
