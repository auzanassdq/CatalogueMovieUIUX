package com.example.sub1_cataloguemovie.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sub1_cataloguemovie.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by auzan on 8/3/2019.
 * Github: @auzanassdq
 */
@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    List<Movie> getAll();

    @Query("SELECT * FROM movie WHERE id = :id")
    Movie getMovie(int id);

    @Insert
    void insertMoive (Movie... movies);

    @Delete
    void deleteMovie(Movie... movies);
}
