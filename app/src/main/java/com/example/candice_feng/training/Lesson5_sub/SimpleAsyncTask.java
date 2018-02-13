package com.example.candice_feng.training.Lesson5_sub;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by candice_feng on 18年2月13日.
 */

class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    // The TextView where we will show results
    private TextView mTextView;

    // Constructor that provides a reference to the TextView from the MainActivity
    public SimpleAsyncTask(TextView tv) {
        mTextView = tv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        HomeWork_7_1.showProgressBar(true);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        HomeWork_7_1.startProgress(values[0]);
    }

    /**
     * Runs on the background thread.
     *
     * @param voids No parameters in this use case.
     * @return Returns the string including the amount of time that
     * the background thread slept.
     */
    @Override
    protected String doInBackground(Void... voids) {

        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        int s = n * 200;

        // Set progress
        publishProgress(s);

        // Sleep for the random amount of time
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return a String result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }


    /**
     * Does something with the result on the UI thread; in this case
     * updates the TextView.
     */
    protected void onPostExecute(String result) {
        mTextView.setText(result);
    }


}