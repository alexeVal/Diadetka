package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;


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

        ArrayList<Lister> arr = new ArrayList<Lister>();

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


    private class OpenHelper extends SQLiteOpenHelper {

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

