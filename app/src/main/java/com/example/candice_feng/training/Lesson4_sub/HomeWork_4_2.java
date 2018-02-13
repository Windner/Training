package com.example.candice_feng.training.Lesson4_sub;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.candice_feng.training.BaseActivity;
import com.example.candice_feng.training.R;

public class HomeWork_4_2 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work_4_2);

        final TextView article_text = findViewById(R.id.article);
        registerForContextMenu(article_text);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        Log.i(TAG, item.toString() + " had been selected.");
        return super.onContextItemSelected(item);
    }

}
