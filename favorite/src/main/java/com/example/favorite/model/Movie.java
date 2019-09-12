package com.example.favorite.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;

/**
 * Created by auzan on 6/29/2019.
 * Github: @auzanassdq
 */
@Entity
public class Movie implements Parcelable {
    @SerializedName("id")
    @NonNull @PrimaryKey
    private int id;

    @SerializedName(value="title", alternate="name")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster")
    private String poster;

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop")
    private String backdrop;

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    private String overview;

    @SerializedName(value="release_date", alternate="first_air_date")
    @ColumnInfo(name = "date")
    private String date;

    @SerializedName("vote_average")
    @ColumnInfo(name = "rating")
    private double rating;

    @ColumnInfo(name = "type")
    private String type;

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        dest.writeDouble(this.rating);
        dest.writeString(this.type);
    }

    private Movie(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.poster = in.readString();
        this.backdrop = in.readString();
        this.overview = in.readString();
        this.date = in.readString();
        this.rating = in.readDouble();
        this.type = in.readString();
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
