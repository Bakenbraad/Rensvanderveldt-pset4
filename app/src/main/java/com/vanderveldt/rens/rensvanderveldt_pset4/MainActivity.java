package com.vanderveldt.rens.rensvanderveldt_pset4;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    // Gets 2 text fields.
    final String[] from = new String[] { SQLiteHelper.SUBJECT, SQLiteHelper.DESC };

    // Destination of the fields.
    final int[] to = new int[] { R.id.choreTV, R.id.descriptionTV };

    // Initialize db manager
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Your todo list.");

        // Create database manager and cursor object:
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor getter = dbManager.fetch();

        // Get listview and create/set adapter.
        ListView lv = (ListView) findViewById(R.id.listView);
        CursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.custom_list_item, getter, from, to, 0);

        lv.setAdapter(adapter);

        float historicX = Float.NaN, historicY = Float.NaN;
        static final int DELTA = 50;
        enum Direction {LEFT, RIGHT;}
        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        historicX = event.getX();
                        historicY = event.getY();
                        break;

                    case MotionEvent.ACTION_UP:
                        if (event.getX() - historicX < -DELTA) {
                            FunctionDeleteRowWhenSlidingLeft();
                            return true;
                        }
                        else if (event.getX() - historicX > DELTA) {
                            FunctionDeleteRowWhenSlidingRight();
                            return true;
                        }
                        break;

                    default:
                        return false;
                }
                return false;
            }
        });
    }

    public void addChore(View view) { Intent goToChoreadd = new Intent(this, ChoreAdd.class);finish(); }

    public Void FunctionDeleteRowWhenSlidingLeft(){
        dbManager.delete();
        return null;
    }

}
