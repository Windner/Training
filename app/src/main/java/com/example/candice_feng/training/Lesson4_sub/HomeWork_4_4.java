package com.example.candice_feng.training.Lesson4_sub;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.candice_feng.training.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomeWork_4_4 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work_4_4);
        new RetrieveFeedTask().execute();
    }
}

class RetrieveFeedTask extends AsyncTask<Void, Void, String> {
    private String API_URL = "http://food2fork.com/api/search";
    private String API_KEY = "";
    private Exception exception;

    protected void onPreExecute() {
        //progressBar.setVisibility(View.VISIBLE);
        //responseView.setText("");
        Log.i("RetrieveFeedTask", "onPreExecute");
    }

    protected String doInBackground(Void... urls) {

        // Do some validation here

        try {
            URL url = new URL(API_URL + "?key=" + API_KEY);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if (response == null) {
            response = "THERE WAS AN ERROR";
        }
        //progressBar.setVisibility(View.GONE);
        Log.i("INFO", response);
        //responseView.setText(response);
    }


}
