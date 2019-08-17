package com.example.sub1_cataloguemovie.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.sub1_cataloguemovie.db.DatabaseClient;
import com.example.sub1_cataloguemovie.db.MovieDao;

import static android.service.notification.Condition.SCHEME;


/**
 * Created by auzan on 10/14/2018.
 * Github: @auzanassdq
 */
public class MovieProvider extends ContentProvider {

    private static final int MOVIE = 100;
    private static final int MOVIE_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final String AUTHORITY = "com.example.sub1_cataloguemovie.provider";
    //    public static final Uri URI_MENU = Uri.parse("content://" + AUTHORITY + "/" + "movie");
    public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath("movie")
            .build();

    static {
        sUriMatcher.addURI(AUTHORITY, "movie", MOVIE);
        sUriMatcher.addURI(AUTHORITY, "movie" + "/*", MOVIE_ID);
    }

//    private MovieHelper movieHelper;

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,@Nullable String[] strings,@Nullable String s,@Nullable String[] strings1,@Nullable String s1) {
        final Cursor cursor;
        MovieDao movieDao = DatabaseClient.getInstance(getContext()).getAppDatabase().movieDao();
//        movHelper = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().movieDao().getMovie(movie.getId());

        switch (sUriMatcher.match(uri)){
            case MOVIE:
                cursor = movieDao.selectAllProvider();
                break;
            case MOVIE_ID:
                cursor = movieDao.selectByIdProvider(ContentUris.parseId(uri));
                break;
            default:
                cursor = null;
                break;
        }

        if (cursor != null){
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri,@Nullable ContentValues contentValues) {
        long added;
        switch (sUriMatcher.match(uri)){
            case MOVIE:
                added = DatabaseClient.getInstance(getContext()).getAppDatabase().movieDao().insertProvider();
                break;
            default:
                added = 0;
                break;
        }

        if (added > 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int updated;
        switch (sUriMatcher.match(uri)){
            case MOVIE_ID:
                updated =9;
                break;
            default:
                updated = 0;
                break;
        }

        if(updated > 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updated;
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int deleted;
        switch (sUriMatcher.match(uri)){
            case MOVIE_ID:
                deleted = DatabaseClient.getInstance(getContext()).getAppDatabase().movieDao().deleteProvider(ContentUris.parseId(uri));
                break;
            default:
                deleted = 0;
                break;
        }

        if (deleted > 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deleted;
    }

}
