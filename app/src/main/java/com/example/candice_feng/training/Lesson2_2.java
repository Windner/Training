package com.example.candice_feng.training;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lesson2_2 extends BaseActivity {

    private static final String COUNT_KEY = "count";
    private TextView countView;
    private Button increaseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2_2);

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

}
