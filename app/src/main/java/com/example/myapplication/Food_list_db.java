package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Food_list_db { // класс базы данных съеденных продуктов

    private static final String DATABASE_NAME = "diadetka_food_list.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "food_list_show";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_XE = "XE";
    private static final String COLUMN_TIME = "Time";

    private static final int NUM_COKUMN_ID = 0;
    private static final int NUM_COLUMN_NAME = 1;
    private static final int NUM_COLUMN_XE = 2;
    private static final int NUM_COLUMN_TIME = 3;

    SQLiteDatabase mDataBase;

    public Food_list_db(Context context) {
        OpenHelper mOpenHelper = new Food_list_db.OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public long insert(String name,String xe,String time) {   // внести запись в базу
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_XE, xe);
        cv.put(COLUMN_TIME,time);
        return mDataBase.insert(TABLE_NAME, null, cv);
    }


    public void delete(long id) {        // удалить запись
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }


    public ArrayList<EatingFood> selectAll() {   // получить все записи
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<EatingFood> arr = new ArrayList();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COKUMN_ID);
                String Name = mCursor.getString(NUM_COLUMN_NAME);
                String Xe = mCursor.getString(NUM_COLUMN_XE);
                String time = mCursor.getString(NUM_COLUMN_TIME);
                arr.add(new EatingFood(Name,Xe,time));

            } while (mCursor.moveToNext());
        }
        return arr;
    }

    public long searchID(String time) { // метод поиска ид

        long id = 0;

        Cursor mCursor = mDataBase.query(TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        mCursor.moveToFirst();

        if (!mCursor.isAfterLast()) {

            do {

                String Time = mCursor.getString(NUM_COLUMN_TIME);

                if (Time.equals(time)) {

                    id = mCursor.getLong(NUM_COKUMN_ID);

                    break;
                }

            } while (mCursor.moveToNext());
        }
        return id;
    }


    private static class OpenHelper extends SQLiteOpenHelper { // класс создания БД

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_XE + " TEXT," +
                    COLUMN_TIME + " TEXT)";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
