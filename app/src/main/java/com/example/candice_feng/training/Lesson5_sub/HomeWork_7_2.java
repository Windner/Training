package com.example.candice_feng.training.Lesson5_sub;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.candice_feng.training.Util.HttpRequestUtil;

import com.example.candice_feng.training.BaseActivity;
import com.example.candice_feng.training.R;

public class HomeWork_7_2 extends BaseActivity {

    private TextView http_textView;
    private EditText url_editText;
    private TextView result_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work_7_2);

        http_textView = findViewById(R.id.http_textView);
        registerForContextMenu(http_textView);

        url_editText = findViewById(R.id.url_text);
        result_textView = findViewById(R.id.url_result);
        final Button get_source_btn = findViewById(R.id.get_source_btn);
        get_source_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //hide soft keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(HomeWork_7_2.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                String url = http_textView.getText().toString() + url_editText.getText();
                new HttpTask().execute(url);
            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.i(TAG, "onCreateContextMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.http_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        Log.i(TAG, item.toString() + " had been selected.");
        http_textView.setText(item.getTitle());
        return super.onContextItemSelected(item);
    }

    class HttpTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            HttpRequestUtil httpRequestUtil = new HttpRequestUtil(getApplicationContext());
            String response = httpRequestUtil.sendGetUrlRequest(urls[0]);
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            result_textView.setText(s);
        }
    }
}
