package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class Food_db {                  // класс для работы с базой данных продуктов

    // создаем константы для работы с базой данных

    private static final String DATABASE_NAME = "diadetka_food.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "food_list";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_URL = "URL";
    private static final String COLUMN_XE = "XE";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_NAME = 1;
    private static final int NUM_COLUMN_URL = 2;
    private static final int NUM_COLUMN_XE = 3;

    SQLiteDatabase mDataBase;

    public Food_db(Context context) {  //создаем базу
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public long insert(String name, String url,String xe) {   // внести запись в базу
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_URL, url);
        cv.put(COLUMN_XE, xe);
        return mDataBase.insert(TABLE_NAME, null, cv);
    }


    public ArrayList<Food> selectAll() {             // получить все записи из базы
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Food> arr = new ArrayList();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                String Name = mCursor.getString(NUM_COLUMN_NAME);
                String URL = mCursor.getString(NUM_COLUMN_URL);
                String Xe = mCursor.getString(NUM_COLUMN_XE);
                arr.add(new Food(Name,URL,Xe));
            } while (mCursor.moveToNext());
        }
        return arr;
    }

    public boolean checkBase(){   // проверить наполненость базы
        boolean check = false;
        if(selectAll().size() == 0){
           check = true;
        }
        return check;
    }

    private static class OpenHelper extends SQLiteOpenHelper {        // класс создания БД

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_URL + " TEXT, " +
                    COLUMN_XE + " TEXT)";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}