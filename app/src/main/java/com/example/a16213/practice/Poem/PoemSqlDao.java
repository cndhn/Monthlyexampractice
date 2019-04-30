package com.example.a16213.practice.Poem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PoemSqlDao {

    PoemSqlite sqlite;

    public PoemSqlDao(Context context) {
        sqlite = new PoemSqlite(context);
    }


    public void insertDao(String title,String writer,String content){
        SQLiteDatabase writableDatabase = sqlite.getWritableDatabase();
        writableDatabase.execSQL("insert into poem1 values(?,?,?)",new String[]{title,writer,content});
        writableDatabase.close();
    }

    public List<PoemInfo> quearyDao(){
        List<PoemInfo> list=new ArrayList <>();
        SQLiteDatabase readableDatabase = sqlite.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select * from poem1", null);
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String writer = cursor.getString(cursor.getColumnIndex("writer"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            list.add(new PoemInfo(title,writer,content));
        }
        return list;
    }
}
