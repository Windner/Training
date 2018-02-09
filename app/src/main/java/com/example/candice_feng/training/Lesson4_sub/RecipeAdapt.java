package com.example.candice_feng.training.Lesson4_sub;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.candice_feng.training.Model.Recipe;
import com.example.candice_feng.training.R;

import java.util.LinkedList;

/**
 * Created by candice_feng on 18年2月9日.
 */

public class RecipeAdapt extends RecyclerView.Adapter<RecipeAdapt.RecipeViewHolder> {
    private static final String TAG = RecipeAdapt.class.getSimpleName();
    private final LinkedList<Recipe> mRecipeList;
    private final LayoutInflater mInflater;

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView recipeTitle;
        public final TextView recipePublisher;

        final RecipeAdapt mAdapter;

        public RecipeViewHolder(View itemView, RecipeAdapt adapter) {
            super(itemView);
            recipeTitle = itemView.findViewById(R.id.recipe_title);
            recipePublisher = itemView.findViewById(R.id.recipe_publisher);

            Log.i(TAG, "RecipeViewHolder");
            this.mAdapter = adapter;
            Log.i(TAG, "Count: " + mAdapter.getItemCount());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Intent intent = new Intent(mInflater.getContext(), HW_4_4_detail.class);
            intent.putExtra("URL", mRecipeList.get(pos).getF2fUrl());
            mInflater.getContext().startActivity(intent);
        }

    }

    public RecipeAdapt(Context context, LinkedList<Recipe> recipeList) {
        mInflater = LayoutInflater.from(context);
        Log.i(TAG, "RecipeAdapt");
        this.mRecipeList = recipeList;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(R.layout.recipe_item, parent, false);
        Log.i(TAG, "onCreateViewHolder");
        return new RecipeViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        // Retrieve the data for that position.
        Recipe mCurrent = mRecipeList.get(position);
        Log.i(TAG, "Position:" + position + ", Title: " + mCurrent.getTitle() + ", ID: " + mCurrent.getID());
        // Add the data to the view holder.
        holder.recipeTitle.setText(mCurrent.getTitle());
        holder.recipePublisher.setText(mCurrent.getPublisher());

    }

    /**
     * Returns the size of the container that holds the data.
     *
     * @return Size of the list of data.
     */
    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

}
