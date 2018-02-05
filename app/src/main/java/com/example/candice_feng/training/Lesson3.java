package com.example.candice_feng.training;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Lesson3 extends AppCompatActivity {
    private static final String TAG = Lesson3.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3);

        Log.i(TAG, "onCreate");

        final Button anrButton = findViewById(R.id.anrBtn);
        anrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lesson3.this, Lesson3_ANR.class));
            }
        });

        final Button mapButton = findViewById(R.id.launchMap);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lesson3.this, Lesson3_map.class));
            }
        });
    }


}
