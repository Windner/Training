package com.example.candice_feng.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Lesson1 extends AppCompatActivity {
    private static final String TAG = Lesson1.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Create a RelativeLayout
        RelativeLayout relativeLayout = new RelativeLayout(this);

        //Create an imageViewer
        ImageView imgView = new ImageView(this);

        //Set ImageViewer resource
        imgView.setImageResource(R.drawable.ic_launcher_foreground);

        //Set layout parameter
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);

        //Add parameter to imageViewer
        imgView.setLayoutParams(lp);

        //Add ImageViewer to RelativeLayout
        relativeLayout.addView(imgView);

        //Set to content view
        setContentView(relativeLayout);

        Log.i(TAG, "onCreate");
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
}
