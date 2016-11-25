package com.vanderveldt.rens.rensvanderveldt_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    // Initialize db manager
    private DBManager dbManager;

    private ListView lv;

    private TodoCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create database manager and cursor object:
        dbManager = new DBManager(this);
        dbManager.open();

        // Create cursor (whitch gets all data) and set cursor to adapter
        Cursor cursor = dbManager.getAll();

        lv = (ListView) findViewById(R.id.listView);

        adapter = new TodoCursorAdapter(this, cursor);
        adapter.notifyDataSetChanged();

        lv.setAdapter(adapter);

        // Listen for edit clicks (short) and pops up an activity to let the user edit their entry.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id_row);
                TextView todoTextView = (TextView) view.findViewById(R.id.title_row);
                TextView descTextView = (TextView) view.findViewById(R.id.desc_row);

                String id = idTextView.getText().toString();
                String todo = todoTextView.getText().toString();
                String desc = descTextView.getText().toString();

                Intent goToEditTodo = new Intent(getApplicationContext(), EditTodo.class);
                goToEditTodo.putExtra("todo", todo);
                goToEditTodo.putExtra("desc", desc);
                goToEditTodo.putExtra("id", id);

                startActivity(goToEditTodo);
            }
        });

        // Listen for delete clicks (long) and throws up a dialog to confirm that you wish to delete the entry.
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                TextView idTextViewdelete = (TextView) findViewById(R.id.id_row);
                final String id_delete = idTextViewdelete.getText().toString();
                dbManager.delete(pos);
                return true;
            }
        });
    }

    public void addChore(View view) {
        Intent goToChoreadd = new Intent(this, ChoreAdd.class);startActivity(goToChoreadd);

    }

}
