package com.example.candice_feng.training;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Lesson2 extends BaseActivity {

    private TextView launch_phone;
    static final int SUM_ACTIVITY_REQUEST = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2);

        launch_phone = findViewById(R.id.launch_phone2);
        launch_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(launch_phone.getText().toString());
            }
        });
        //set launchCamera
        final Button launchCamera = findViewById(R.id.intentBtn);
        launchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        //EditText
        final EditText number1 = findViewById(R.id.number1);
        final EditText number2 = findViewById(R.id.number2);

        //Sum button
        final Button sumBtn = findViewById(R.id.sumBtn);
        sumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] values = new String[2];
                values[0] = number1.getText().toString();
                values[1] = number2.getText().toString();

                //create intent and start activity
                Intent intent = new Intent(Lesson2.this, Lesson2_sum.class);
                intent.putExtra(Lesson2_sum.VALUES_KEY, values);
                startActivityForResult(intent, SUM_ACTIVITY_REQUEST);
            }
        });

        //homework 2.2
        final Button hw2_2 = findViewById(R.id.hw2_2Btn);
        hw2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lesson2.this, Lesson2_2.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult");
        if (requestCode == SUM_ACTIVITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, getString(R.string.sum) + " : " + data.getStringExtra(Lesson2_sum.RESULT_KEY), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
