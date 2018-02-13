package com.example.candice_feng.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Lesson7 extends BaseActivity {

    /**
     * Post-study exercises:
     * (1) Create an app and target api level to O.
     * (2) Create an Activity which can read all contacts in this device and be able to list them all in a list view.
     * (3) After 2 finished, go to settings->Apps->Find your app->Permissions->Disable Contacts permission, and go back to your app to check if everything is working fine.
     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson7);
    }
}
