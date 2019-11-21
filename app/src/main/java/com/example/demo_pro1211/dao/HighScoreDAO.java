package com.example.demo_pro1211.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.demo_pro1211.database.Database;
import com.example.demo_pro1211.model.HighScore;

import java.util.ArrayList;
import java.util.List;

public class HighScoreDAO {
    public static String TABLE_NAME = "HighScore";
    private SQLiteDatabase db;
    private Database database;
    public static final String TAG = "HighScoreDAO";

    public static final String SQL_HIGHSCORE = "" +
            "CREATE TABLE HighScore(id int primary key," +
            "name text,diem text);";

    public HighScoreDAO(Context context) {
        database = new Database(context);
        db = database.getWritableDatabase();
        db = database.getReadableDatabase();
    }

    public boolean insertHighscore(HighScore highScore) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", highScore.getId());
        contentValues.put("name", highScore.getName());
        contentValues.put("diem", highScore.getDiem());
        long result = db.insert(TABLE_NAME, null, contentValues);
        try {
            if (result < 0) {
                return false;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
            return false;
        }
        return true;
    }

    public List<HighScore> selectHighScore() {
        List<HighScore> highScoreList = new ArrayList<>();
        String select = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            do {
                HighScore highScore = new HighScore();
                highScore.setId(cursor.getInt(0));
                highScore.setName(cursor.getString(1));
                highScore.setDiem(cursor.getString(2));
                highScoreList.add(highScore);

            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return highScoreList;
    }
}
