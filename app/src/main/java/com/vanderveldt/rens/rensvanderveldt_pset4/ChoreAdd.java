package com.vanderveldt.rens.rensvanderveldt_pset4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ChoreAdd extends AppCompatActivity {

    // Edittext initilized.
    EditText todoName;
    EditText description;

    // Start a DB manager.
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_add);

        // Open connection.
        dbManager = new DBManager(this);
        dbManager.open();

        // Get edittexts.
        todoName = (EditText) findViewById(R.id.todoED);
        description = (EditText) findViewById(R.id.descriptionED);
    }

    public void addChoreAct(View view) {

        // Get text from edittext upon button press
        String name = todoName.getText().toString();
        String desc = description.getText().toString();

        dbManager.insert(name, desc);

        Intent goToMain = new Intent(this, MainActivity.class);
        startActivity(goToMain);
    }
}
