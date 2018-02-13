package com.example.candice_feng.training.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by candice_feng on 18年2月12日.
 */

public final class HttpRequestUtil {

    private static Context mContext;
    private static ConnectivityManager conMgr;
    private static final String TAG = "HttpRequestUtil";

    public HttpRequestUtil(Context context) {
        Log.i(TAG, "HttpRequestUtil");
        this.mContext = context;
        conMgr = (ConnectivityManager) mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);

    }

    public static boolean isNetworkConnected() {
        if (conMgr == null)
            return false;
        NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        boolean connected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return connected;
    }

    public static String sendGetUrlRequest(String strUrl) {
        Log.i(TAG, "sendGetUrlRequest: " + strUrl);
        if (isNetworkConnected()) {

            try {
                URL url = new URL(strUrl);
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
                Log.e(TAG, e.getMessage(), e);
                return e.getMessage();
            }
        }

        return "Please check your network!";
    }
}
