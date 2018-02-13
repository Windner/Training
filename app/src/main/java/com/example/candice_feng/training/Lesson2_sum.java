package com.example.candice_feng.training;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lesson2_sum extends BaseActivity {
    public static final String VALUES_KEY = "values";
    public static final String RESULT_KEY = "result";
    private TextView sumTv;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2_sum);
        sumTv = findViewById(R.id.resultTv);
        sumTv.setText(getString(R.string.no_input));
        String result = getString(R.string.no_input);

        //Get input values and calculate
        Intent intent = getIntent();
        String[] values = intent.getStringArrayExtra(VALUES_KEY);
        if (values != null)
            result = getResult(values);

        //show result
        sumTv.setText(result);

        //Back button
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(RESULT_KEY, sumTv.getText().toString());
                setResult(Activity.RESULT_OK, resultIntent);
                Log.d(TAG, "End SecondActivity");
                finish();
            }
        });
    }

    private String getResult(String[] values) {
        int result = 0;

        for (int i = 0; i < values.length; i++) {
            if (!values[i].isEmpty())
                result += Integer.parseInt(values[i]);
        }

        return String.valueOf(result);
    }

}
