package com.example.favmovie.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import static com.example.favmovie.db.DatabaseContract.FavoriteColumns.TABLE_MOVIE;

/**
 * Created by auzan on 10/4/2018.
 * Github: @auzanassdq
 */
public class DatabaseContract {

    public  static final class FavoriteColumns implements BaseColumns {
        public static String TABLE_MOVIE = "movie_favorite";

//        public static String ID = "_id";
        public static String TITLE = "title";
        public static String OVERVIEW = "overview";
        public static String RELEASE = "releaseDate";
        public static String RATING = "rating";
        public static String POSTER = "poster";
        public static String BACKDROP = "backdrop";
    }

    public static final String AUTHORITY = "com.example.auzan.cataloguemovieuiux";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_MOVIE)
            .build();

    public static String getColumnString(Cursor cursor, String colomnName) {
        return cursor.getString(cursor.getColumnIndex(colomnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

}
