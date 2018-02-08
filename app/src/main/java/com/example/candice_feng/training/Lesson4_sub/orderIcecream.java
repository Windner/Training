package com.example.candice_feng.training.Lesson4_sub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.candice_feng.training.R;

public class orderIcecream extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_icecream);
        getSupportActionBar().setTitle(R.string.order_icecream);
    }
}
