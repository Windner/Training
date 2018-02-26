package com.example.candice_feng.training;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.candice_feng.training.Model.ToDoAdapt;
import com.example.candice_feng.training.Model.ToDoItem;
import com.example.candice_feng.training.Model.ToDoItem.TodoDBHelper;

import java.util.LinkedList;

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

    private RecyclerView mRecyclerView;
    private ToDoAdapt mToDoAdapt;
    private LinkedList<ToDoItem> mToDoList;

    private ImageButton mAddButton;
    private EditText mTodoContent;

    private TodoDBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson6);

        //editView
        mTodoContent = findViewById(R.id.todo_content);

        //add button setup
        mAddButton = findViewById(R.id.add_todoBtn);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add item to DB
                //Data: CheckBox state: false, mTodoContent text, currentTime
            }
        });

        //set application bar menu

        //Add option menu to show completed tasks.


        //init DB
        //sort by create date
        mDBHelper = new TodoDBHelper(this);

        // Create recycler view.
        //Reference Homework 4.4
        mRecyclerView = findViewById(R.id.todo_listView);
        //add divider
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL) {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 3;
                super.getItemOffsets(outRect, view, parent, state);
            }
        });

        mToDoAdapt = new ToDoAdapt(this, mToDoList);
        mRecyclerView.setAdapter(mToDoAdapt);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}

/**
 * Lesson 6 - Shared Preferences, SQLite and ContentProvider
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
 * Additional resources:
 * SharedPreferences
 * SharedPreferences: https://developer.android.com/reference/android/content/SharedPreferences.html
 * Saving Key-Value Sets: https://developer.android.com/training/basics/data-storage/shared-preferences.html
 * https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/en/Unit%204/91_p_shared_preferences.html
 * SQLite databases
 * Saving Data in SQL Databases: http://developer.android.com/training/basics/data-storage/databases.html
 * SQLiteDatabase class: https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html
 * SQLiteOpenHelper class: https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper.html
 * ContentValues class: http://developer.android.com/reference/android/content/ContentValues.html
 * Cursor class: https://developer.android.com/reference/android/database/Cursor.html
 * https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/en/Unit%204/101a_p_sqlite_data_storage.html
 * https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/en/Unit%204/101b_p_searching_an_sqlite_database.html
 * Content Providers
 * Content Providers: https://developer.android.com/guide/topics/providers/content-providers.html
 * MatrixCursor: https://developer.android.com/reference/android/database/MatrixCursor.html
 * https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/en/Unit%204/111a_p_implement_a_minimalist_content_provider.html
 * https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/en/Unit%204/111b_p_add_a_content_provider_to_wordlistsql.html
 **/