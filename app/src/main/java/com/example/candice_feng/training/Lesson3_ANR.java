package com.example.candice_feng.training;

import android.os.SystemClock;
import android.os.Bundle;

public class Lesson3_ANR extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3__anr);
        SystemClock.sleep(20000);
    }

}
