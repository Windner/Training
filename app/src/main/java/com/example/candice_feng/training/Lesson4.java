package com.example.candice_feng.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.candice_feng.training.Lesson4_sub.*;

public class Lesson4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson4);

        getSupportActionBar().setTitle(R.string.lesson_four);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Button hw4_1Btn = findViewById(R.id.hw4_1);
        hw4_1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lesson4.this, HomeWork_4_1.class));
            }
        });

        final Button hw4_2Btn = findViewById(R.id.hw4_2);
        hw4_2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lesson4.this, HomeWork_4_2.class));
            }
        });

        final Button hw4_4Btn = findViewById(R.id.hw4_4);
        hw4_4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lesson4.this, HomeWork_4_4.class));
            }
        });
    }

    public void orderFroyo(View view) {
        startActivity(new Intent(Lesson4.this, orderFroyo.class));
    }

    public void orderDonut(View view) {
        startActivity(new Intent(Lesson4.this, orderDonut.class));

    }

    public void orderIcecream(View view) {
        startActivity(new Intent(Lesson4.this, orderIcecream.class));

    }
}
