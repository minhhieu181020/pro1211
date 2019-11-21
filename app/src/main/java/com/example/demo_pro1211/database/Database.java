package com.example.demo_pro1211.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.demo_pro1211.dao.HighScoreDAO;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbAiLaTrieuPhu";

    private static final int version = 1;


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
sqLiteDatabase.execSQL(HighScoreDAO.SQL_HIGHSCORE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + HighScoreDAO.TABLE_NAME);
    }
}
