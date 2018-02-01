package com.example.candice_feng.training;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Lesson2 extends AppCompatActivity {
    private static final String TAG = Lesson2.class.getSimpleName();
    private TextView launch_phone;
    static final int SUM_ACTIVITY_REQUEST = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2);
        Log.i(TAG, "onCreate");

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
                intent.putExtra("values", values);
                startActivityForResult(intent, SUM_ACTIVITY_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult");
        if (requestCode == SUM_ACTIVITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, getString(R.string.sum) + " : " + data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG, "onPostResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
