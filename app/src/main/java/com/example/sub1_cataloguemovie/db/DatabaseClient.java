package com.example.sub1_cataloguemovie.db;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by auzan on 8/6/2019.
 * Github: @auzanassdq
 */
public class DatabaseClient {

    private Context context;
    private static DatabaseClient mInstance;
    private AppDatabase appDatabase;

    private DatabaseClient (Context context){
        this.context = context;
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "movie").build();
    }

    public static synchronized DatabaseClient getInstance(Context context){
        if (mInstance == null){
            mInstance = new DatabaseClient(context);
        }

        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
