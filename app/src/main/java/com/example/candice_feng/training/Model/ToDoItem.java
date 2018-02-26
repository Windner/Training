package com.example.candice_feng.training.Model;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MIYAFENG1 on 2018/2/27.
 */

public class ToDoItem {

    //private  int mId;
    private boolean mCompleted;
    private String mToDoContent;
    private String mCreateTime;
    private String mFinishedTime;

    public ToDoItem(String todoContent, String createTime) {
        this.mCompleted = false;
        this.mToDoContent = todoContent;
        //get create here?
        this.mCreateTime = createTime;
    }

    public void setCompleted(boolean bCompleted) {
        this.mCompleted = bCompleted;
    }

    public void setTodoContent(String todoContent) {
        this.mToDoContent = todoContent;
    }

    public void setFinishedTime(String time) {
        //set finish time directly?
        this.mFinishedTime = time;
    }

    public boolean getCompleted() {
        return this.mCompleted;
    }

    public String getToDoContent() {
        return this.mToDoContent;
    }

    public String getCreateTime() {
        return this.mCreateTime;
    }

    public String getFinishedTime() {
        return this.mFinishedTime;
    }

    public class TodoDBHelper extends SQLiteOpenHelper {

        //DB...

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "ToDo.db";
        public static final String TODO_LIST_TABLE = "todo_entries";

        //Column
        public static final String KEY_ID = "_id";
        public static final String KEY_STATUS = "completed_status";
        public static final String KEY_CONTENT = "content";
        public static final String KEY_CREATE_DATE = "create_date";
        public static final String KEY_FINISHED_DATE = "finished_date";

        //String array if columns
        /*
        private static final String[] COLUMNS = {
                KEY_ID,
                KEY_STATUS,
                KEY_CONTENT,
                KEY_CREATE_DATE,
                KEY_FINISHED_DATE };
                */

        private static final String TEXT_TYPE = " TEXT";
        private static final String INTEGER_TYPE = " INTEGER";
        private static final String DATETIME_TYPE = " DATETIME";
        private static final String COMMA_SEP = ",";
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TODO_LIST_TABLE + " (" +
                        KEY_ID + " INTEGER PRIMARY KEY," +
                        KEY_STATUS + INTEGER_TYPE + " DEFAULT 0" + COMMA_SEP +
                        KEY_CONTENT + TEXT_TYPE + COMMA_SEP +
                        KEY_CREATE_DATE + DATETIME_TYPE + " DEFAULT CURRENT_TIMESTAMP" + COMMA_SEP  +
                        KEY_FINISHED_DATE + DATETIME_TYPE + " DEFAULT NELL" +
                        " )";

        public TodoDBHelper(Context context) {
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL("DROP TABLE IF EXISTS " + TODO_LIST_TABLE);

            onCreate(db);
        }


    }

}
