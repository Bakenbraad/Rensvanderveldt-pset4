package com.vanderveldt.rens.rensvanderveldt_pset4;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Rens on 25-11-2016.
 */

public class TodoCursorAdapter extends CursorAdapter {
    public TodoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_list_item, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvTitle = (TextView) view.findViewById(R.id.title_row);
        TextView tvID = (TextView) view.findViewById(R.id.id_row);
        TextView tvDesc = (TextView) view.findViewById(R.id.desc_row);
        // Extract properties from cursor
        String title = cursor.getString(cursor.getColumnIndexOrThrow("subject"));
        String desc = cursor.getString(cursor.getColumnIndexOrThrow("description"));
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        // Populate fields with extracted properties
        tvTitle.setText(title);
        tvDesc.setText(desc);
        tvID.setText(String.valueOf(id));
    }
}
