package com.jamesvuong.week1_flicks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvuonger on 10/10/16.
 */

public class Movie {
    private int mMovieId;
    private String mTitle;
    private String mOverview;
    private String mPosterPath;
    private String mBackdropPath;

    public Movie() {}

    public String getTitle() {
        return mTitle;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getOverview() {
        return mOverview;
    }

    public Movie fromJsonObject(JSONObject jsonObject) {
        try {

            mMovieId = jsonObject.getInt("id");
            mTitle = jsonObject.getString("title");
            mOverview = jsonObject.getString("overview");
            mPosterPath = jsonObject.getString("poster_path");
            mBackdropPath = jsonObject.getString("backdrop_path");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;
    }

    public static List<Movie> fromJsonArray(JSONArray jsonArray) {
        List<Movie> moviesArrayList = new ArrayList<Movie>();
        Movie currentMovie;

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                currentMovie = new Movie();
                moviesArrayList.add(currentMovie.fromJsonObject(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return moviesArrayList;
    }
}
