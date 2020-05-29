package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class List_db {

    private static final String DATABASE_NAME = "diadetka_list.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "list_show";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TIME = "Time";
    private static final String COLUMN_TEXT = "Text";
    private static final String COLUMN_ISRED = "isred";

    private static final int NUM_COKUMN_ID = 0;
    private static final int NUM_COLUMN_TIME = 1;
    private static final int NUM_COLUMN_TEXT = 2;
    private static final int NUM_COLUMN_ISRED = 3;


    SQLiteDatabase mDataBase;

    public List_db(Context context) {  // создать БД
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public long insert(String time, String text, int isRed) { // добавить запись
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_TEXT, text);
        cv.put(COLUMN_ISRED, isRed);
        return mDataBase.insert(TABLE_NAME, null, cv);
    }


    public void delete(long id) {  // удалить запись
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }


    public long searchID(String text) { // метод поиска ид

        long id = 0;

        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        mCursor.moveToFirst();

        if (!mCursor.isAfterLast()) {

            do {

                String Text = mCursor.getString(NUM_COLUMN_TEXT);

                if (Text.equals(text)) {

                    id = mCursor.getLong(NUM_COKUMN_ID);

                    break;
                }

            } while (mCursor.moveToNext());
        }
        return id;
    }

    public ArrayList<Lister> selectAll() {      // получаем все записи
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Lister> arr = new ArrayList();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COKUMN_ID);
                String time = mCursor.getString(NUM_COLUMN_TIME);
                String text = mCursor.getString(NUM_COLUMN_TEXT);
                int isRed = mCursor.getInt(NUM_COLUMN_ISRED);
                arr.add(new Lister(time, text, isRed));

            } while (mCursor.moveToNext());
        }
        return arr;
    }
    public String getTextForTime(String time){   // получаем текст для напоминания

        String txt = "0";

        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        mCursor.moveToFirst();

        if (!mCursor.isAfterLast()) {

            do {

                String Time = mCursor.getString(NUM_COLUMN_TIME);

                if (Time.equals(time)) {

                    txt = mCursor.getString(NUM_COLUMN_TEXT);

                    break;
                }

            } while (mCursor.moveToNext());
        }

        return txt;
    }

    private static class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TIME + " TEXT, " +
                    COLUMN_TEXT + " TEXT," +
                    COLUMN_ISRED + " INTEGER)";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
