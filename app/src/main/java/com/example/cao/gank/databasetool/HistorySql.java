package com.example.cao.gank.databasetool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/9/5.
 */

public class HistorySql extends SQLiteOpenHelper {
    private String CREATE_TABLE ="create table if not exists history (_id integer primary key autoincrement,keyName text)";
    public HistorySql(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
