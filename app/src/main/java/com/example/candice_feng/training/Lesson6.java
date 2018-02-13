package com.example.candice_feng.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Lesson6 extends BaseActivity {

    /**
     * Pre-study:
     * Concept:
     * https://docs.google.com/presentation/d/1TvillPkl0oxiCPvac06sq9XZ3c3N3mbYZD-WktD1JDQ
     * Shared preferences:
     * https://docs.google.com/presentation/d/1uubIPfieKYZns7zT9__P8w9KlO_5UFIxxhTuVTnbi-s
     * SQLite primer:
     * https://docs.google.com/presentation/d/1UAx3OBYRNurWkW1hh-Ck_zWKx77sxlw5LG9IAz4UHKE
     * Store data using SQLite database:
     * https://docs.google.com/presentation/d/1bPn1Ry9enE3dukp9lJIZQcZHxQTbVzIq2xP8Y5BX1RU
     * ContentProviders:
     * https://docs.google.com/presentation/d/1fVCNdzCTOnzbEeyAWOwGkflS_4dBSKRd0VtPDZW3K6E
     * <p>
     * Post-study:
     * Build a TODO app
     * 1.The user can add new items to the list.
     * 2.Each item in the database includes a task to do, creation and completion dates, and whether or not the task has been completed.
     * 3.When the app starts, the screen shows a list of incomplete to-do items sorted by creation date.
     * 4.The UI includes an Options menu item to start an activity that shows the completed tasks.
     * 5.The user can change an item's completion status. When the user marks a task as completed, it is marked complete and the creation date is replaced with the completion date.
     * 6.When the user taps an incomplete item, edit mode is triggered, and the user can edit the item.
     * 7.When the app is restarted, the latest state of items is visible, which demonstrates that the data was saved and reloaded.
     **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson6);
    }
}
