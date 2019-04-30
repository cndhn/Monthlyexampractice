package com.example.a16213.practice.Poem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PoemSqlite extends SQLiteOpenHelper{
    public PoemSqlite(Context context) {
        super(context,"day430.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table poem1 (title text,writer text,content text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
