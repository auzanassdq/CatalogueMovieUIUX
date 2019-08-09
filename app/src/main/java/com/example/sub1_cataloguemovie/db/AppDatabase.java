package com.example.sub1_cataloguemovie.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.sub1_cataloguemovie.model.Movie;

/**
 * Created by auzan on 8/3/2019.
 * Github: @auzanassdq
 */

@Database(entities = {Movie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}
