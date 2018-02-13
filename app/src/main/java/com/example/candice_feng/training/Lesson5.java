package com.example.candice_feng.training;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.candice_feng.training.Lesson5_sub.*;

public class Lesson5 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson5);

        final Button hw_7_1_btn = findViewById(R.id.hw7_1);
        hw_7_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lesson5.this, HomeWork_7_1.class));
            }
        });

        final Button hw_7_2_btn = findViewById(R.id.hw7_2);
        hw_7_2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lesson5.this, HomeWork_7_2.class));
            }
        });

        final Button hw_8_3_btn = findViewById(R.id.hw8_3);
        hw_8_3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lesson5.this, HomeWork_8_3.class));
            }
        });
    }
}
