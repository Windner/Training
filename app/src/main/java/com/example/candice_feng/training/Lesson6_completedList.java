package com.example.candice_feng.training;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.candice_feng.training.Model.ToDoAdapt;
import com.example.candice_feng.training.Model.ToDoItem;
import com.example.candice_feng.training.Util.ToDoDBHelper;

import java.util.LinkedList;

public class Lesson6_completedList extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ToDoAdapt mToDoAdapt;
    private LinkedList<ToDoItem> mCompletedList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson6_completed_list);
        getSupportActionBar().setTitle("Completed List");

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        // Create recycler view.
        //Reference Homework 4.4
        mRecyclerView = findViewById(R.id.completed_list);
        //add divider
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL) {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 3;
                super.getItemOffsets(outRect, view, parent, state);
            }
        });

        mCompletedList = ToDoDBHelper.getmInstance(this).getAllItems(ToDoItem.STATE_COMPLETEED);
        mToDoAdapt = new ToDoAdapt(this, mCompletedList, ToDoAdapt.TTYPE_COMPLETED);
        mRecyclerView.setAdapter(mToDoAdapt);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
