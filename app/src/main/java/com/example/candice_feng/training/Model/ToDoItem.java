package com.example.candice_feng.training.Model;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by MIYAFENG1 on 2018/2/27.
 */

public class ToDoItem {

    private static String TAG = ToDoItem.class.getSimpleName();

    public static int STATE_ONGOING = 0;
    public static int STATE_COMPLETEED = 1;

    private long _Id;
    private int _State;
    private String _Content;
    private Date _CreateTime;
    private Date _FinishedTime;

    public ToDoItem(String todoContent) {
        this._State = STATE_ONGOING;
        this._Content = todoContent;
        this._CreateTime = new Date();
    }

    public ToDoItem(int id, int state, String content, int createTime, int finishedTime) {
        this._Id = id;
        this._State = state;
        this._Content = content;
        this._CreateTime = new Date(createTime);
        this._FinishedTime = new Date(finishedTime);
    }

    public ToDoItem() {
    }

    //getting ID
    public long getID() {
        return this._Id;
    }

    //getting State
    public int getState() {
        return this._State;
    }

    //getting Content
    public String getContent() {
        return this._Content;
    }

    //getting CreateTime
    public Date getCreateTime() {
        return this._CreateTime;
    }

    //getting FinishTime
    public Date getFinishedTime() {
        return this._FinishedTime;
    }

    //setting ID
    public void setID(long id) {
        this._Id = id;
    }

    //setting State
    public void setState(int state) {
        this._State = state;
    }

    //setting Content
    public void setContent(String content) {
        this._Content = content;
    }

    //setting CreateTime
    public void setCreateTime(long time) {
        if (this._CreateTime == null)
            this._CreateTime = new Date(time);
        else
            Log.i(TAG, "CreateTime is exist.");
    }

    //setting FinishTime
    public void setFinishedTime(long time) {
        this._FinishedTime = new Date(time);
    }

    //set Completed
    public void setCompleted(boolean isCompleted) {
        if (isCompleted) {
            this._State = STATE_COMPLETEED;
            Date now = new Date();
            this.setFinishedTime(now.getTime());
        } else {
            this._State = STATE_ONGOING;
            //Update db will fail if set time to null
            this._FinishedTime = new Date(0);

        }
    }
}
