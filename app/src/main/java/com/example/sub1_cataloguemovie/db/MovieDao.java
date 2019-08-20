package com.example.sub1_cataloguemovie.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.sub1_cataloguemovie.model.Movie;

import java.util.List;

/**
 * Created by auzan on 8/3/2019.
 * Github: @auzanassdq
 */
@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    List<Movie> getAll();

    @Query("SELECT * FROM movie WHERE type = :type")
    List<Movie> getAllMovie(String type);

    @Query("SELECT * FROM movie WHERE type = :type")
    List<Movie> getAllTvShow(String type);

    @Query("SELECT * FROM movie WHERE id = :id")
    Movie getMovie(int id);

    @Insert
    void insertMoive (Movie... movies);

    @Delete
    void deleteMovie(Movie... movies);

    @Query("SELECT * FROM movie")
    Cursor selectAllProvider();

    @Query("SELECT * FROM movie WHERE id = :id")
    Cursor selectByIdProvider(long id);

    @Query("SELECT * FROM movie WHERE type = :type")
    Cursor selectAllTvShowProvider(String type);

    @Query("SELECT * FROM movie WHERE type = :type")
    Cursor selectAllMovieProvider(String type);

    @Insert
    long insertProvider(Movie movie);

    @Delete
    int deleteProvider(long id);
}
