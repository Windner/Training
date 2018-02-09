package com.example.candice_feng.training.Lesson4_sub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.candice_feng.training.R;


public class HW_4_4_detail extends AppCompatActivity {
    private static final String TAG = HW_4_4_detail.class.getSimpleName();

    //TODO: use query recipe detail and native ui
    //http://food2fork.com/api/get?key={KEY}&rId={recipe_id}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw_4_4_detail);
        Log.i(TAG, "onCreate");
        getSupportActionBar().setTitle(R.string.recipe_detail);
        WebView myWebView = findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebChromeClient(new WebChromeClient());
        //Get input values and calculate
        Intent intent = getIntent();
        String url = intent.getStringExtra("URL");
        Log.i(TAG, url);
        if (url != null)
            myWebView.loadUrl(url);
    }


}
