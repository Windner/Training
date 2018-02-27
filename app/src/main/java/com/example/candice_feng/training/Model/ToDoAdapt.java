package com.example.candice_feng.training.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.candice_feng.training.R;
import com.example.candice_feng.training.Util.ToDoDBHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by MIYAFENG1 on 2018/2/27.
 */

public class ToDoAdapt extends RecyclerView.Adapter<ToDoAdapt.ToDoViewHolder> {
    private static final String TAG = ToDoAdapt.class.getSimpleName();
    //private final LinkedList<ToDoItem> mTodoList;
    private final LayoutInflater mInflater;
    private ToDoDBHelper mDB;

    private OnItemClickListener onItemClickListener;

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
            mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //update db
                    int pos = getAdapterPosition();
                    ToDoItem item = mDB.getTodoItem(pos);
                    item.setCompleted(isChecked);
                    mDB.updateItemState(item);


                }
            });
        }

        @Override
        public void onClick(View v) {
            //Tap item to edit
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(v, mDB.getTodoItem(getAdapterPosition()));
        }

    }

    public ToDoAdapt(Context context, ToDoDBHelper db) {
        mInflater = LayoutInflater.from(context);
        Log.i(TAG, "ToDoList");
        //this.mTodoList = todoList;
        this.mDB = db;
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
        ToDoItem item = mDB.getTodoItem(position);
        boolean isCompleted = item.getState() == 0 ? false : true;
        holder.mCheckBox.setChecked(isCompleted);
        holder.mToDoContent.setText(item.getContent());
        if (isCompleted)
            holder.mDate.setText(CovertDateToString(item.getFinishedTime()));
        else
            holder.mDate.setText(CovertDateToString(item.getCreateTime()));
    }

    @Override
    public int getItemCount() {
        return mDB.getcount();
    }

    public String CovertDateToString(Date date) {

        if (date != null) {
            try {
                if (IsToday(date)) {
                    //show time
                    DateFormat time_formatter = DateFormat.getTimeInstance();
                    return time_formatter.format(date);
                } else {
                    //show date
                    DateFormat day_formatter = DateFormat.getDateInstance();
                    return day_formatter.format(date);
                }
            } catch (ParseException e) {
                Log.e(TAG, e.getMessage());
            }
            return "Error";
        } else
            return "Empty";
    }

    public boolean IsToday(Date date) throws ParseException {

        //Today
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        //date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;

    }

    public void setOnItemClickListener(ToDoAdapt.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onItemClick(View view, ToDoItem item);

    }

}
