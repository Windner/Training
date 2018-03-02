package com.example.candice_feng.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set to content view
        setContentView(R.layout.activity_main);
        final Button lesson1_btn = findViewById(R.id.lesson_one_btn);
        lesson1_btn.setText(R.string.lesson_one);
        lesson1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Lesson1.class));
            }
        });

        final Button lesson2_btn = findViewById(R.id.lesson_two_btn);
        lesson2_btn.setText(R.string.lesson_two);
        lesson2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Lesson2.class));
            }
        });

        final Button lesson3_btn = findViewById(R.id.lesson_three_btn);
        lesson3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Lesson3.class));
            }
        });

        final Button lesson4_btn = findViewById(R.id.lesson_four_btn);
        lesson4_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Lesson4.class));
            }
        });

        final Button lesson5_btn = findViewById(R.id.lesson_five_btn);
        lesson5_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Lesson5.class));
            }
        });

        final Button lesson6_btn = findViewById(R.id.lesson_six_btn);
        lesson6_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Lesson6.class));
            }
        });

        final Button lesson7_btn = findViewById(R.id.lesson_seven_btn);
        lesson7_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Lesson7.class));
            }
        });
        Log.i(TAG, "onCreate");
    }

}
