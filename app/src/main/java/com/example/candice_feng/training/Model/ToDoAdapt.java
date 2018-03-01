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
import android.widget.EditText;
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
    public static int TYPE_ONGOING = 0;
    public static int TTYPE_COMPLETED = 1;

    private static final String TAG = ToDoAdapt.class.getSimpleName();
    private final LinkedList<ToDoItem> mTodoList;
    private final LayoutInflater mInflater;

    private RecyclerView mRecyclerView;
    private int mListType;

    class ToDoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

        final ToDoAdapt mAdapter;

        public CheckBox mCheckBox;
        public TextView mToDoContent;
        public TextView mDate;
        public EditText mEditView;

        public TextView tvCompletedContent;
        public TextView tvCompletedDate;

        public ToDoViewHolder(View itemView, ToDoAdapt adapter) {
            super(itemView);
            if (mListType == TYPE_ONGOING) {
                mCheckBox = itemView.findViewById(R.id.todo_checkbox);
                mToDoContent = itemView.findViewById(R.id.todo_content_textView);
                mEditView = itemView.findViewById(R.id.todo_content_editView);
                mEditView.setFocusableInTouchMode(true);
                mEditView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            //update item
                            final int pos = getAdapterPosition();
                            ToDoItem item = mTodoList.get(pos);
                            item.setContent(mEditView.getText().toString());

                            mTodoList.set(pos, item);

                            //change edit view to text view
                            mEditView.setVisibility(View.GONE);
                            mToDoContent.setVisibility(View.VISIBLE);

                            //update ui
                            updateItemLayout(pos);
                        }
                    }
                });
                itemView.setOnClickListener(this);
                mDate = itemView.findViewById(R.id.todo_date_textView);
                Log.i(TAG, "ToDoViewHolder");
            } else {
                tvCompletedContent = itemView.findViewById(R.id.completed_content_textView);
                tvCompletedDate = itemView.findViewById(R.id.completed_date_textView);
            }

            this.mAdapter = adapter;
            Log.i(TAG, "Count: " + mAdapter.getItemCount());
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.getId() == R.id.todo_checkbox) {
                final int pos = getAdapterPosition();
                ToDoItem item = mTodoList.get(pos);
                //update checkbox
                item.setCompleted(isChecked);
                //mDB.updateItem(item, mDB.KEY_STATUS);
                mTodoList.set(pos, item);
                updateItemLayout(pos);
            }
        }

        @Override
        public void onClick(View v) {
            //hide textview
            mToDoContent.setVisibility(View.INVISIBLE);
            //set text on edit view
            mEditView.setText(mToDoContent.getText());
            //edit view visible
            mEditView.setVisibility(View.VISIBLE);

            mEditView.requestFocus();
        }

        /**
         * Solution: update ui while RecyclerView available. Otherwise will crash due to update
         * while recyclerView is updating or scrolling.
         * java.lang.IllegalStateException: Cannot call this method while RecyclerView is computing a layout or scrolling
         */
        public void updateItemLayout(final int pos) {

            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    notifyItemChanged(pos);
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    public ToDoAdapt(Context context, LinkedList<ToDoItem> list, int type) {
        mInflater = LayoutInflater.from(context);
        Log.i(TAG, "ToDoList");
        this.mTodoList = list;
        this.mListType = type;
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = null;
        if (mListType == TYPE_ONGOING)
            mItemView = mInflater.inflate(R.layout.todo_item, parent, false);
        else
            mItemView = mInflater.inflate(R.layout.completed_item, parent, false);
        Log.i(TAG, "onCreateViewHolder");

        return new ToDoAdapt.ToDoViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {

        ToDoItem item = mTodoList.get(position);
        if (mListType == TYPE_ONGOING) {
            boolean isCompleted = item.getState() == 0 ? false : true;
            holder.mCheckBox.setChecked(isCompleted);
            holder.mCheckBox.setOnCheckedChangeListener(holder);
            holder.mToDoContent.setText(item.getContent());
            if (isCompleted)
                holder.mDate.setText(CovertDateToString(item.getFinishedTime()));
            else
                holder.mDate.setText(CovertDateToString(item.getCreateTime()));
            Log.i(TAG, "onBindViewHolder: " + position + "  Content: " + item.getContent());

        } else {
            holder.tvCompletedContent.setText(item.getContent());
            holder.tvCompletedDate.setText(CovertDateToString(item.getFinishedTime()));
       }
    }

    @Override
    public int getItemCount() {
        return mTodoList.size();
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

}
