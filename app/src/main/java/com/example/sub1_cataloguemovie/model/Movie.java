package com.example.sub1_cataloguemovie.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by auzan on 6/29/2019.
 * Github: @auzanassdq
 */
public class Movie implements Parcelable {
    private String name, desc, photo, year;

    public Movie() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    private Movie(Parcel in) {
        this.name = in.readString();
        this.desc = in.readString();
        this.photo = in.readString();
        this.year = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeString(photo);
        dest.writeString(year);
    }
}
