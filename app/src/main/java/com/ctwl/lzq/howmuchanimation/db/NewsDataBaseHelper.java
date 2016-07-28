package com.ctwl.lzq.howmuchanimation.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ctwl.lzq.howmuchanimation.Utils.LogUtils;

/**
 * Created by h0nes1pr09rammer on 2016/7/27.
 */
public class NewsDataBaseHelper extends SQLiteOpenHelper{

    public NewsDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        LogUtils.i("NewsDataBaseHelper","-----db onCreate---");
        db.execSQL("create table user(id int, name varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtils.i("NewsDataBaseHelper","-----db onUpgrade---");
    }
}
