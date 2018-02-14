package com.example.candice_feng.training.Lesson4_sub;

import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.candice_feng.training.BaseActivity;
import com.example.candice_feng.training.Model.Recipe;
import com.example.candice_feng.training.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HomeWork_4_4 extends BaseActivity {
    private RecyclerView mRecyclerView;
    private RecipeAdapt mAdapter;
    private final LinkedList<Recipe> mRecipeList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work_4_4);
        getSupportActionBar().setTitle(R.string.recipe_list);
        new RetrieveFeedTask().execute();

        // Create recycler view.
        mRecyclerView = findViewById(R.id.recyclerView);

        //add divider
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL) {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 3;
                super.getItemOffsets(outRect, view, parent, state);
            }
        });

        // Create an adapter and supply the data to be displayed.
        mAdapter = new RecipeAdapt(this, mRecipeList);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        Log.i(TAG, "setRecipeAdapt");
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {
        private String API_URL = "http://food2fork.com/api/search";
        private String API_KEY = "3ddc7a65c38eca0b1633148d995317e1";


        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
        }

        protected String doInBackground(Void... urls) {
            Log.i(TAG, "doInBackground");
            //query recipe list
            //http://food2fork.com/api/search?key={KEY}&q={query string}
            //query recipe detail
            //http://food2fork.com/api/get?key={KEY}&rId={recipe_id}
            //Reference http://food2fork.com/about/api


            ConnectivityManager conMgr = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);

            if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) {

                // notify user you are online
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
                    Log.e(TAG, e.getMessage(), e);
                    return null;
                }

            } else if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().getState() == NetworkInfo.State.DISCONNECTED) {
                Log.i(TAG, "no connection.");
                showToast("Please check your network.");
            }

            return null;
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR. Please check your network.";
                showToast(response);
            } else {
                parseResponse(response);
                Log.i(TAG, "notifyDataSetChanged");
                // Notify the adapter, that the data has changed.
                mAdapter.notifyDataSetChanged();
            }
        }

        public void parseResponse(String response) {
            JSONObject recipeObj;
            List<Recipe> recipeList;
            try {
                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();

                JSONArray recipes = object.getJSONArray(Recipe.RECIPES);
                recipeList = new ArrayList<Recipe>(recipes.length());
                Log.i(TAG, "Recipe ocunt: " + recipes.length());
                for (int i = 0; i < recipes.length(); i++) {
                    Log.i(TAG, recipes.getJSONObject(i).toString());
                    recipeObj = (JSONObject) new JSONTokener(recipes.getJSONObject(i).toString()).nextValue();
                    recipeList.add(new Recipe(
                            recipeObj.getString(Recipe.RECIPE_ID),
                            recipeObj.getString(Recipe.TITLE),
                            recipeObj.getString(Recipe.PUBLISHER),
                            recipeObj.getString(Recipe.F2F_URL),
                            recipeObj.getDouble(Recipe.SOCIAL_RANK),
                            recipeObj.getString(Recipe.PUBLISHER_URL)
                    ));

                }
                mRecipeList.addAll(recipeList);

            } catch (JSONException e) {
                // Appropriate error handling code
                Log.e(TAG, e.getMessage(), e);
            }

        }
    }
}


