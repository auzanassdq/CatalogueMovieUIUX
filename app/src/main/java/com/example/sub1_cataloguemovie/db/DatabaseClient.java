package com.example.sub1_cataloguemovie.db;

import android.arch.persistence.room.Room;
import android.content.Context;

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
}
