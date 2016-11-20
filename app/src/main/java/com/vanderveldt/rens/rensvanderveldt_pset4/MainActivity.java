package com.vanderveldt.rens.rensvanderveldt_pset4;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView lv;

    // Gets 2 text fields.
    final String[] from = new String[] { SQLiteHelper.SUBJECT, SQLiteHelper.DESC };

    // Destination of the fields.
    final int[] to = new int[] { R.id.choreTV, R.id.descriptionTV };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create database manager and cursor object:
        final DBManager dbManager = new DBManager(this);
        dbManager.open();
        Cursor getter = dbManager.fetch();

        // Get listview and create/set adapter.
        lv = (ListView) findViewById(R.id.listView);
        CursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.custom_list_item, getter, from, to, 0);

        lv.setAdapter(adapter);
    }

    public void addChore(View view) {

    }

    public Integer updateChoreCount() {

        Integer chorecount;

        return chorecount;
    }
}
