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

    // Constructor
    public DBManager(Context c) {
        context = c;
    }

    // Opens database, gets writable connection.
    public DBManager open() throws SQLException {
        dbHelper = new SQLiteHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    // Closes connection
    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(SQLiteHelper.SUBJECT, name);
        contentValue.put(SQLiteHelper.DESC, desc);
        database.insert(SQLiteHelper.TABLE_NAME, null, contentValue);
    }
    // Fetches all records. (Wrap this in cursor adapter to pass to listview!)
    public Cursor fetch() {
        String[] columns = new String[] { SQLiteHelper._ID, SQLiteHelper.SUBJECT, SQLiteHelper.DESC };
        Cursor cursor = database.query(SQLiteHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Updates value in list
    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelper.SUBJECT, name);
        contentValues.put(SQLiteHelper.DESC, desc);
        int i = database.update(SQLiteHelper.TABLE_NAME, contentValues, SQLiteHelper._ID + " = " + _id, null);
        return i;
    }

    // Deletes record
    public void delete(long _id) {
        database.delete(SQLiteHelper.TABLE_NAME, SQLiteHelper._ID + "=" + _id, null);
    }

}
