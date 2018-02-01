package com.example.candice_feng.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lesson2_2 extends AppCompatActivity {
    private static final String TAG = Lesson2_2.class.getSimpleName();
    private static final String COUNT_KEY = "count";
    private TextView countView;
    private Button increaseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2_2);

        Log.i(TAG, "onCreate");

        countView = findViewById(R.id.countTv);
        increaseBtn = findViewById(R.id.increaseBtn);
        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase();
            }
        });

        //restore state
        if (savedInstanceState != null) {
            countView.setText(savedInstanceState.getString(COUNT_KEY));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(COUNT_KEY, countView.getText().toString());
    }

    private void increase() {
        int count = Integer.parseInt(countView.getText().toString());
        count += 1;
        countView.setText(String.valueOf(count));
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG, "onPostResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
