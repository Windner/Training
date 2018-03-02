package com.example.candice_feng.training;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.candice_feng.training.dummy.ContactAdapter;
import java.util.List;

public class Lesson7 extends BaseActivity {

    /**
     * Post-study exercises:
     * (1) Create an app and target api level to O.
     * (2) Create an Activity which can read all contacts in this device and be able to list them all in a list view.
     * (3) After 2 finished, go to settings->Apps->Find your app->Permissions->Disable Contacts permission, and go back to your app to check if everything is working fine.
     **/

    ContactAdapter mDBHelper;
    MyContactRecyclerViewAdapter mAdapter;
    RecyclerView mRecyclerView;
    List<ContactAdapter.ContactItem> mContactList;
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson7);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
                Toast.makeText(this, "Please enable permission for read contacts.", Toast.LENGTH_SHORT);

            }
        } else {
            mDBHelper = new ContactAdapter(getApplicationContext());
            mContactList = mDBHelper.getData();

            setupRecyclerView();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.i(TAG, "onRequestPermissionsResult: requestCode: " + requestCode +
                " permission: " + permissions[0]);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    mDBHelper = new ContactAdapter(getApplicationContext());
                    mContactList = mDBHelper.getData();

                    setupRecyclerView();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Please enable permission for read contacts.", Toast.LENGTH_SHORT);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    private void setupRecyclerView() {
        // Create recycler view.
        //Reference Homework 4.4
        mRecyclerView = findViewById(R.id.contact_list);
        //add divider
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL) {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 3;
                super.getItemOffsets(outRect, view, parent, state);
            }
        });

        mAdapter = new MyContactRecyclerViewAdapter(mContactList);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
