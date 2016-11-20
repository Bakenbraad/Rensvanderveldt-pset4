package com.vanderveldt.rens.rensvanderveldt_pset4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Rens on 20-11-2016.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "TODO_LIST";

    // Table columns
    public static final String _ID = "_id";
    public static final String SUBJECT = "location";
    public static final String DESC = "description";

    // DB name
    static final String DB_NAME = "TODO_REGISTRY.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "(" + _ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " " +
            "TEXT NOT NULL, " + DESC + " TEXT);";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // Uses create table string to construct SQL creation request.
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Resets version, upgrades tables.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

