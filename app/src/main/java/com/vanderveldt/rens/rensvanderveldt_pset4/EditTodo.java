package com.vanderveldt.rens.rensvanderveldt_pset4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditTodo extends AppCompatActivity {

    private long _id;
    private DBManager dbManager;

    EditText todoText;
    EditText descText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Edit Chore");
        setContentView(R.layout.activity_edit_todo);

        dbManager = new DBManager(this);
        dbManager.open();

        todoText = (EditText) findViewById(R.id.todo_edittext);
        descText = (EditText) findViewById(R.id.description_edittext);

        Bundle extras = getIntent().getExtras();
        // Get extras from intent
        String id = extras.getString("id");
        String name = extras.getString("todo");
        String desc = extras.getString("desc");
        // Turn id into long id
        _id = Long.parseLong(id);

        // Show user which to-do they are editing
        todoText.setText(name);
        descText.setText(desc);
    }

    // Back function
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
        finish();
    }

    // When update is pressed
    public void updateChore(View view) {
        String title = todoText.getText().toString();
        String desc = descText.getText().toString();
        if (title.length() == 0){
            Toast.makeText(this, "Please enter a To-Do", Toast.LENGTH_SHORT).show();
            if (desc.length() == 0){
                Toast.makeText(this, "Please enter a description", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            dbManager.update(_id, title, desc);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            this.returnHome();
        }
    }
}

