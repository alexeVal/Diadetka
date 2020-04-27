package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class List_db {

    private static final String DATABASE_NAME = "diadetka_list_food.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "list";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TIME = "Time";
    private static final String COLUMN_TEXT = "Text";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_TIME = 1;
    private static final int NUM_COLUMN_TEXT = 2;

    String month,minute,day,hour;


    SQLiteDatabase mDataBase;

    public List_db(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public long insert(String Time, String Text) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TIME, Time);
        cv.put(COLUMN_TEXT, Text);
        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    public int update(Lister md) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TIME, md.getTime());
        cv.put(COLUMN_TEXT, md.getText());
        return mDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?",new String[] { String.valueOf(md.getId())});
    }

    public void deleteAll() {
        mDataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(long id) {
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public Lister select(long id) {

        Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        mCursor.moveToFirst();
        String Time = mCursor.getString(NUM_COLUMN_TIME);
        String Text = mCursor.getString(NUM_COLUMN_TEXT);
        return new Lister(Time,Text);
    }

    public ArrayList<Lister> selectAll() {

        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Lister> arr = new ArrayList();

        mCursor.moveToFirst();

        if (!mCursor.isAfterLast()) {

            do {
                String Time = mCursor.getString(NUM_COLUMN_TIME);

                String Text = mCursor.getString(NUM_COLUMN_TEXT);

                arr.add(new Lister(Time,Text));

            } while (mCursor.moveToNext());
        }
        return arr;
    }

    public long searchID(String text) {

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

                String Text = mCursor.getString(NUM_COLUMN_TEXT);

                if (Text.equals(text)) {

                    id = mCursor.getLong(NUM_COLUMN_ID);

                    break;
                }

            } while (mCursor.moveToNext());
        }
        return id;
    }
    String getStringDate(){

        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM");
        Date date = new Date();
        String time = timeFormat.format(date);
        String da = dateFormat.format(date);
        Log.w("txt",da + " " + time);
        return da + " " + time ;
    }

     public String getTextForTime (){

        String text = "0";

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

                 if (Time.equals(getStringDate())) {

                     text = mCursor.getString(NUM_COLUMN_TEXT);

                     break;
                 }

             } while (mCursor.moveToNext());
         }
         return text;

     }


    private static class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TIME + " TEXT," +
                    COLUMN_TEXT + " TEXT)" ;

            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

            onCreate(db);
        }
    }
}

