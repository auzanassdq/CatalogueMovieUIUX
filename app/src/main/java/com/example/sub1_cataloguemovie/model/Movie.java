package com.example.sub1_cataloguemovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by auzan on 6/29/2019.
 * Github: @auzanassdq
 */
public class Movie implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName(value="title", alternate={"name"})
    private String title;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("backdrop_path")
    private String backdrop;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String date;

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.poster);
        dest.writeString(this.backdrop);
        dest.writeString(this.overview);
        dest.writeString(this.date);
    }

    private Movie(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.poster = in.readString();
        this.backdrop = in.readString();
        this.overview = in.readString();
        this.date = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
