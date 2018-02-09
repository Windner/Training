package com.example.candice_feng.training.Model;

import java.net.URL;

/**
 * Created by candice_feng on 18年2月8日.
 */

public class Recipe {
    public static final String COUNT = "count";
    public static final String RECIPES = "recipes";
    public static final String PUBLISHER = "publisher";
    public static final String F2F_URL = "f2f_url";
    public static final String TITLE = "title";
    public static final String SOURCE_URL = "source_url";
    public static final String RECIPE_ID = "recipe_id";
    public static final String IMAGE_URL = "image_url";
    public static final String SOCIAL_RANK = "social_rank";
    public static final String PUBLISHER_URL = "publisher_url";

    private String mRecipe_id;
    private String mTitle;
    private String mPublisher;
    private String mF2F_url;
    private Double mSocial_rank;
    private String mSource_url;

    public Recipe(String id,
                  String title,
                  String publisher,
                  String f2f_url,
                  Double social_rank,
                  String source_url) {
        this.mRecipe_id = id;
        this.mTitle = title;
        this.mPublisher = publisher;
        this.mF2F_url = f2f_url;
        this.mSocial_rank = social_rank;
        this.mSource_url = source_url;
    }

    public String getID() {
        return this.mRecipe_id;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getPublisher() {
        return this.mPublisher;
    }

    public String getF2fUrl() {
        return this.mF2F_url;
    }

    public String getSourceurl() {
        return this.mSource_url;
    }

    public Double getSocialRank() {
        return this.mSocial_rank;
    }
}
