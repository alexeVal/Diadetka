package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class VK_ID_base { // класс БД с id VK

        private static final String DATABASE_NAME = "VK_ID.db";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_NAME = "table_Id";

        private static final String COLUMN_ID = "id";
        private static final String COLUMN_USERID = "UserID";

        private static final int NUM_COLUMN_ID = 0;
        private static final int NUM_COLUMN_VKID = 1;

        private SQLiteDatabase mDataBase;

        public VK_ID_base(Context context) {  // создать БД
            OpenHelper mOpenHelper = new OpenHelper(context);
            mDataBase = mOpenHelper.getWritableDatabase();
        }

        public long insert(int UserID) {        // получить запись
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_USERID, UserID);
            return mDataBase.insert(TABLE_NAME, null, cv);
        }

    public void deleteAll() {
        mDataBase.delete(TABLE_NAME, null, null);
    }

        public ArrayList<Integer> selectAll() {     // получить все записи
            Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

            ArrayList<Integer> arr = new ArrayList();
            mCursor.moveToFirst();
            if (!mCursor.isAfterLast()) {
                do {
                    long id = mCursor.getLong(NUM_COLUMN_ID);
                    int UserId = mCursor.getInt(NUM_COLUMN_VKID);
                    arr.add(UserId);
                } while (mCursor.moveToNext());
            }
            return arr;
        }

        private class OpenHelper extends SQLiteOpenHelper { // класс создания БД

            OpenHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                String query = "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_USERID + " INT);";
                db.execSQL(query);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                onCreate(db);
            }
        }
    }

