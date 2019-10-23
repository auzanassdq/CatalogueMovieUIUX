package com.example.sub1_cataloguemovie.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;

import static android.os.Build.VERSION.RELEASE;
import static android.provider.BaseColumns._ID;
import static com.example.sub1_cataloguemovie.db.DatabaseClient.getColumnInt;
import static com.example.sub1_cataloguemovie.db.DatabaseClient.getColumnString;

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
    private String rating;

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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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
        dest.writeString(this.rating);
        dest.writeString(this.type);
    }

    private Movie(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.poster = in.readString();
        this.backdrop = in.readString();
        this.overview = in.readString();
        this.date = in.readString();
        this.rating = in.readString();
        this.type = in.readString();
    }

    public Movie (Cursor cursor) {
        this.id = getColumnInt(cursor, "id");
        this.title = getColumnString(cursor, "title");
        this.poster = getColumnString(cursor, "poster");
        this.backdrop = getColumnString(cursor, "backdrop");
        this.overview = getColumnString(cursor, "overview");
        this.date = getColumnString(cursor, "date");
        this.rating = getColumnString(cursor, "rating");
        this.type = getColumnString(cursor, "type");
    }

    public static Movie fromContentValues(ContentValues values) {
        final Movie movie = new Movie();
        movie.id = values.getAsInteger("id");
        movie.title = values.getAsString("title");
        movie.poster = values.getAsString("poster");
        movie.backdrop = values.getAsString("backdrop");
        movie.overview = values.getAsString("overview");
        movie.date = values.getAsString("date");
        movie.rating = values.getAsString("rating");
        movie.type = values.getAsString("type");
//        if (values.containsKey("id")) {
//            movie.id = values.getAsInteger("id");
//        }
//        if (values.containsKey("title")) {
//            movie.title = values.getAsString("title");
//        }
        return movie;
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
