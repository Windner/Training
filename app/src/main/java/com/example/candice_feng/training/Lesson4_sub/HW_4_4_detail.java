package com.example.candice_feng.training.Lesson4_sub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.candice_feng.training.BaseActivity;
import com.example.candice_feng.training.R;


public class HW_4_4_detail extends BaseActivity {

    //TODO: use query recipe detail and native ui
    //http://food2fork.com/api/get?key={KEY}&rId={recipe_id}
    /*
    {
        "recipe":
        {
            "publisher": "Closet Cooking",
            "f2f_url": "http://food2fork.com/view/35368",
            "ingredients":
            [
                "1/2 (10 ounce) package frozen spinach, thawed, drained and coarsely chopped",
                "1 (14 ounce) can artichoke hearts, drained and coarsely chopped",
                "4 ounces cream cheese, room temperature",
                "1/2 cup sour cream",
                "1/4 cup mayonnaise",
                "1 clove garlic, grated",
                "1/2 teaspoon chili sauce (optional)",
                "1/4 cup grated parmigiano reggiano (parmesan), grated",
                "1/4 cup mozzarella, shredded\n"
            ],
            "source_url": "http://www.closetcooking.com/2008/11/hot-spinach-and-artichoke-dip.html", "recipe_id": "35368",
            "image_url": "http://static.food2fork.com/HotSpinachandArtichokeDip5007579cdf5.jpg",
            "social_rank": 100.0,
            "publisher_url": "http://closetcooking.com",
            "title": "Hot Spinach and Artichoke Dip"
        }
    }
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw_4_4_detail);
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
