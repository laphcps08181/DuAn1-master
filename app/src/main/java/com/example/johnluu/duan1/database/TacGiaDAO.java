package com.example.johnluu.duan1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.johnluu.duan1.SQLite.DBHelper;
import com.example.johnluu.duan1.model.TacGia;


import java.util.ArrayList;

public class TacGiaDAO {
    SQLiteDatabase db;
    Context c;
    DBHelper dbh;

    public TacGiaDAO(Context c) {
        this.c=c;
        dbh=new DBHelper(c);
    }

    public void ThemTacGia(TacGia tgl){
        db = dbh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tentacgia",tgl.tentacgia);
        db.insert("tacgia",null,values);
    }

    public ArrayList<TacGia> xemTacGia(){
        ArrayList<TacGia> dstacgia = new ArrayList<TacGia>();
        dbh = new DBHelper(c);
        db = dbh.getReadableDatabase();
        Cursor c = db.rawQuery("select * from tacgia",null);
        if(c.moveToFirst()){
            do{
                int _idtacgia = c.getInt(0);
                String tentacgia = c.getString(1);
                TacGia tacgia = new TacGia(_idtacgia,tentacgia);
                dstacgia.add(tacgia);
            }while(c.moveToNext());
        }
        return dstacgia;
    }

    public void xoaTacGia(int _id){
        db = dbh.getWritableDatabase();
        db.delete("tacgia","_idtacgia=?",new String[]{_id+""});
    }

    public void suaTacgia(TacGia tg){
        db = dbh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tentacgia",tg.tentacgia);
        db.update("tacgia",values,"_idtacgia = ?",new String[]{tg._idtacgia+""});
    }
}