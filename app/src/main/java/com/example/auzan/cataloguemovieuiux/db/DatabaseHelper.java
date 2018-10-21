package com.example.auzan.cataloguemovieuiux.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.auzan.cataloguemovieuiux.db.DatabaseContract.FavoriteColumns.BACKDROP;
import static com.example.auzan.cataloguemovieuiux.db.DatabaseContract.FavoriteColumns.OVERVIEW;
import static com.example.auzan.cataloguemovieuiux.db.DatabaseContract.FavoriteColumns.POSTER;
import static com.example.auzan.cataloguemovieuiux.db.DatabaseContract.FavoriteColumns.RATING;
import static com.example.auzan.cataloguemovieuiux.db.DatabaseContract.FavoriteColumns.RELEASE;
import static com.example.auzan.cataloguemovieuiux.db.DatabaseContract.FavoriteColumns.TABLE_MOVIE;
import static com.example.auzan.cataloguemovieuiux.db.DatabaseContract.FavoriteColumns.TITLE;

/**
 * Created by auzan on 10/4/2018.
 * Github: @auzanassdq
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbmovie";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String CREATE_TABLE_MOVIE = "CREATE TABLE " + TABLE_MOVIE +
            " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TITLE + " TEXT NOT NULL, " +
            OVERVIEW + " TEXT NOT NULL, " +
            RELEASE + " TEXT NOT NULL, " +
            RATING + " TEXT NOT NULL, " +
            POSTER + " TEXT NOT NULL, " +
            BACKDROP + " TEXT NOT NULL);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }
}
