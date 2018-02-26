package com.example.candice_feng.training.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.candice_feng.training.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by MIYAFENG1 on 2018/2/27.
 */

public class ToDoAdapt extends RecyclerView.Adapter<ToDoAdapt.ToDoViewHolder> {
    private static final String TAG = ToDoAdapt.class.getSimpleName();
    private final LinkedList<ToDoItem> mTodoList;
    private final LayoutInflater mInflater;

    class ToDoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ToDoAdapt mAdapter;

        public final CheckBox mCheckBox;
        public final TextView mToDoContent;
        public final TextView mDate;

        public ToDoViewHolder(View itemView, ToDoAdapt adapter) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.todo_checkbox);
            mToDoContent = itemView.findViewById(R.id.todo_content_textView);
            mDate = itemView.findViewById(R.id.todo_date_textView);

            Log.i(TAG, "ToDoViewHolder");
            this.mAdapter = adapter;
            Log.i(TAG, "Count: " + mAdapter.getItemCount());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            //Tap item to edit

        }

    }

    public ToDoAdapt(Context context, LinkedList<ToDoItem> todoList) {
        mInflater = LayoutInflater.from(context);
        Log.i(TAG, "ToDoList");
        this.mTodoList = todoList;
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(R.layout.todo_item, parent, false);
        Log.i(TAG, "onCreateViewHolder");
        return new ToDoAdapt.ToDoViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mTodoList.size();
    }
}
