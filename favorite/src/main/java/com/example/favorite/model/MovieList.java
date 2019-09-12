package com.example.favorite.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by auzan on 7/13/2019.
 * Github: @auzanassdq
 */
public class MovieList {
    @SerializedName("results")
    private ArrayList<Movie> results;

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}
