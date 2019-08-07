package com.example.sub1_cataloguemovie;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.sub1_cataloguemovie.adapter.MovieAdapter;
import com.example.sub1_cataloguemovie.db.DatabaseClient;
import com.example.sub1_cataloguemovie.model.Movie;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class FavoriteActivity extends AppCompatActivity {

    private List<Movie> srcDB;
    private ArrayList<Movie> movies;
    final public static String KEY_MOVIES = "key_movies";
    private RecyclerView rvCategory;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        initViews();
        getFav();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        initViews();
                        getFav();
                    }
                }, 2000);
            }
        });
    }

    private void initViews() {
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        swipeRefreshLayout = findViewById(R.id.sw_layout);
        rvCategory = findViewById(R.id.rv_list);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getFav(){
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                srcDB = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().movieDao().getAll();
                movies = new ArrayList<>(srcDB);
                generateMovieList(movies);
            }

        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d("Get Fav", "onComplete: " + movies);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Get Fav", "onErroe: " + movies);
            }
        });
    }

    private void generateMovieList(ArrayList<Movie> movies) {
        MovieAdapter adapter = new MovieAdapter(this);
        adapter.setListMovie(movies);
        rvCategory.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initViews();
        getFav();
    }

}
