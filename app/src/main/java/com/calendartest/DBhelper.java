package com.calendartest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "Event.db";
    private final static int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE_SQL = "CRETATE TABLE IF NOT EXISTS Event" +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + "Name TEXT,Event Text,Date_from TEXT,Time_from TEXT" +
            "Date_to TEXT,Time_to TEXT,RepeatFrequency TEXT,Privacy TEXT,Remind TEXT)";

    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {   //建立資料表
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

}
