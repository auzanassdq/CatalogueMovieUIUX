package com.example.auzan.cataloguemovieuiux.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieItem implements Parcelable {

    private String title;
    private String overview;
    private String releaseDate;
    private String urlGambar;
    private String backdrop;
    private String voteAverage;

    public MovieItem(JSONObject object) {
        try {

            String title = object.getString("original_title");
            String overview = object.getString("overview");
            String releaseDate = object.getString("release_date");
            String poster = object.getString("poster_path");
            String background = object.getString("backdrop_path");
            String voteAverage = object.getString("vote_average");

            this.title = title;
            this.overview = overview;
            this.releaseDate = releaseDate;
            this.urlGambar = poster;
            this.voteAverage = voteAverage;

            if (!releaseDate.equals("")){
                this.releaseDate = releaseDate.substring(0,4);
            } else{
                releaseDate = "";
            }
            this.backdrop = background;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getUrlGambar() {
        return urlGambar;
    }

    public void setUrlGambar(String url_gambar) {
        this.urlGambar = url_gambar;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }


    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeString(this.urlGambar);
        dest.writeString(this.backdrop);
        dest.writeString(this.voteAverage);
    }

    protected MovieItem(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.urlGambar = in.readString();
        this.backdrop = in.readString();
        this.voteAverage = in.readString();
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel source) {
            return new MovieItem(source);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };

}
