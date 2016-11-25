package com.vanderveldt.rens.rensvanderveldt_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


// Create Read Update and Delete operations are preformed here.

public class DBManager {

    private SQLiteHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new SQLiteHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }



    public void close() {
        dbHelper.close();
    }

    public Cursor getAll(){
        return database.rawQuery("SELECT  * FROM " + SQLiteHelper.TABLE_NAME ,null);
    }

    public void insert(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(SQLiteHelper.SUBJECT, name);
        contentValue.put(SQLiteHelper.DESC, desc);
        database.insert(SQLiteHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { SQLiteHelper._ID, SQLiteHelper.SUBJECT, SQLiteHelper.DESC };
        Cursor cursor = database.query(SQLiteHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelper.SUBJECT, name);
        contentValues.put(SQLiteHelper.DESC, desc);
        int i = database.update(SQLiteHelper.TABLE_NAME, contentValues, SQLiteHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(SQLiteHelper.TABLE_NAME, SQLiteHelper._ID + "=" + _id, null);
    }

}
