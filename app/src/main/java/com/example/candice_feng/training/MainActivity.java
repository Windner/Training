package com.example.candice_feng.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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
    }


}
