package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class Shugar_db {
    private static final String DATABASE_NAME = "diadetka_shugar.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "levels";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LEVEL = "level";
    private static final String COLUMN_TIME = "time";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_LEVEL = 1;
    private static final int NUM_COLUMN_TIME = 2;

    SQLiteDatabase mDataBase;

    public Shugar_db (Context context) {       // создать БД
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public long insert(double level,String time) {   //внести запись в БД
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LEVEL, level);
        cv.put(COLUMN_TIME, time);
        return mDataBase.insert(TABLE_NAME, null, cv);
    }


    public void delete(long id) {           // удалить запись
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }


    public long searchID(double levelS) {      // найти id

        long id = 0;

        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        mCursor.moveToFirst();

        if (!mCursor.isAfterLast()) {

            do {

                double level = mCursor.getDouble(NUM_COLUMN_LEVEL);

                if (level == levelS) {

                    id = mCursor.getLong(NUM_COLUMN_ID);

                    break;
                }

            } while (mCursor.moveToNext());
        }
        return id;
    }


    public ArrayList<Shugar> selectAll() {   // получить все записи
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Shugar> arr = new ArrayList();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                double level = mCursor.getDouble(NUM_COLUMN_LEVEL);
                String time = mCursor.getString(NUM_COLUMN_TIME);
                arr.add(new Shugar(level,time));

            } while (mCursor.moveToNext());
        }
        return arr;
    }

    private static class OpenHelper extends SQLiteOpenHelper {  // класс создания БД

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_LEVEL + " REAL," + COLUMN_TIME + " TEXT)";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}
