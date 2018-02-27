package com.example.candice_feng.training.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.candice_feng.training.Model.ToDoItem;

import java.util.LinkedList;

import static java.lang.Math.toIntExact;

/**
 * Created by candice_feng on 18年2月27日.
 */

public class ToDoDBHelper extends SQLiteOpenHelper {

    private static String TAG = ToDoDBHelper.class.getSimpleName();
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

    private static final String[] COLUMNS = {
            KEY_ID,
            KEY_STATUS,
            KEY_CONTENT,
            KEY_CREATE_DATE,
            KEY_FINISHED_DATE};


    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DATETIME_TYPE = " DATETIME";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TODO_LIST_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY," +
                    KEY_STATUS + INTEGER_TYPE + " DEFAULT 0" + COMMA_SEP +
                    KEY_CONTENT + TEXT_TYPE + COMMA_SEP +
                    KEY_CREATE_DATE + INTEGER_TYPE + COMMA_SEP +
                    KEY_FINISHED_DATE + INTEGER_TYPE + " DEFAULT NULL" +
                    " )";


    private static final String SQL_QUERY_ALL_ITEMS = "SELECT * FROM " +
            TODO_LIST_TABLE;

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    public ToDoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    //Upgrade DB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + TODO_LIST_TABLE);
        onCreate(db);
    }

    /**
     * Add new ToDo item
     */
    public void addTodo(ToDoItem item) {
        ContentValues values = new ContentValues();
        values.put(KEY_STATUS, item.getState());
        values.put(KEY_CONTENT, item.getContent());
        values.put(KEY_CREATE_DATE, item.getCreateTime().getTime());

        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            //Insert
            mWritableDB.insert(TODO_LIST_TABLE, null, values);

        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e.getMessage());
        }
    }

    /**
     * Get Single item
     */
    public ToDoItem getTodoItem(int position) {
        String SQL_QUERY_SINGLE_ITEM = "SELECT * FROM " +
                TODO_LIST_TABLE + " ORDER BY " + KEY_CREATE_DATE + " DESC " +
                " LIMIT " + position + ",1";
        ToDoItem item = new ToDoItem();
        Cursor cursor = null;
        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(SQL_QUERY_SINGLE_ITEM, null);

            if (cursor != null) {
                cursor.moveToFirst();

                item.setID(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
                item.setState(cursor.getInt(cursor.getColumnIndex(KEY_STATUS)));
                item.setContent(cursor.getString(cursor.getColumnIndex(KEY_CONTENT)));
                item.setCreateTime(cursor.getLong(cursor.getColumnIndex(KEY_CREATE_DATE)));
                item.setFinishedTime(cursor.getLong(cursor.getColumnIndex(KEY_FINISHED_DATE)));
            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            cursor.close();
            return item;
        }
    }

    /**
     * Get All items
     */
    public LinkedList<ToDoItem> getAllItems() {
        LinkedList<ToDoItem> list = new LinkedList<>();
        Cursor cursor = null;
        try {
            if (mWritableDB == null)
                mWritableDB = getWritableDatabase();
            cursor = mWritableDB.rawQuery(SQL_QUERY_ALL_ITEMS, null);

            if (cursor.moveToFirst()) {
                do {
                    ToDoItem item = new ToDoItem();
                    item.setID(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
                    item.setState(cursor.getInt(cursor.getColumnIndex(KEY_STATUS)));
                    item.setContent(cursor.getString(cursor.getColumnIndex(KEY_CONTENT)));
                    item.setCreateTime(cursor.getLong(cursor.getColumnIndex(KEY_CREATE_DATE)));
                    item.setFinishedTime(cursor.getLong(cursor.getColumnIndex(KEY_FINISHED_DATE)));
                    list.add(item);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            cursor.close();
            return list;
        }
    }


    // Get Ongoing items, sort by CreateTime
    // Get Completed items

    /**
     * Get item total count
     */
    public int getcount() {
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }
        return toIntExact(DatabaseUtils.queryNumEntries(mReadableDB, TODO_LIST_TABLE));
    }

    // Update Single item
    public void updateItemState(ToDoItem item) {
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(KEY_STATUS, item.getState());
            mWritableDB.update(TODO_LIST_TABLE,
                    values,
                    KEY_ID + " = ?",
                    new String[]{String.valueOf(item.getID())});
        } catch (Exception e) {
            Log.d(TAG, "UPDATE EXCEPTION! " + e.getMessage());
        }
    }
    // Delete Single item

    // Close db

}
