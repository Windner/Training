package com.example.candice_feng.training;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.candice_feng.training.Lesson5_sub.HomeWork_7_2;
import com.example.candice_feng.training.Model.ToDoAdapt;
import com.example.candice_feng.training.Model.ToDoItem;
import com.example.candice_feng.training.Util.ToDoDBHelper;

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

    private static ToDoDBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson6);

        //init DB
        mDBHelper = ToDoDBHelper.getmInstance(this);
        mToDoList = mDBHelper.getAllItems(ToDoItem.STATE_ONGOING);
        //editView
        mTodoContent = findViewById(R.id.todo_content);

        //add button setup
        mAddButton = findViewById(R.id.add_todoBtn);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add item to DB
                //Data: CheckBox state: false, mTodoContent text, currentTime
                String content = mTodoContent.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    ToDoItem item = mDBHelper.addTodo(new ToDoItem(content));
                    if (item != null)
                        mToDoList.addFirst(item);
                }
                else
                    Log.i(TAG, "Todo item is empty, fail to add item.");

                //update ui
                mToDoAdapt.notifyDataSetChanged();
                mTodoContent.setText("");
                //hide soft keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(HomeWork_7_2.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setupRecyclerView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lesson6_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.start_completed_list:
                mDBHelper.UpdateItems(mToDoList);
                startActivity(new Intent(Lesson6.this, Lesson6_completedList.class));
                break;

            default:
                break;
        }

        return true;
    }

    private void setupRecyclerView() {
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

        mToDoAdapt = new ToDoAdapt(this, mToDoList, ToDoAdapt.TYPE_ONGOING);
        mToDoAdapt.setViewListener(new ToDoAdapt.ViewListener() {
            @Override
            public void onItemClick(int position, ToDoItem item) {
                mDBHelper.updateItem(item, mDBHelper.KEY_CONTENT);
            }

            @Override
            public void onCheckedChanged(int pos, ToDoItem item, boolean isChecked) {
                mDBHelper.updateItem(item, mDBHelper.KEY_STATUS);
            }
        });
        mRecyclerView.setAdapter(mToDoAdapt);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onStop() {
        super.onStop();
        //update DB
        mDBHelper.UpdateItems(mToDoList);
    }

}
