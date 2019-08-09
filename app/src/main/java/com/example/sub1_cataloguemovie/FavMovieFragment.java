package com.example.sub1_cataloguemovie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * Created by auzan on 8/8/2019.
 * Github: @auzanassdq
 */
public class FavMovieFragment extends Fragment {

    private List<Movie> srcDB;
    private ArrayList<Movie> movies;
    final public static String KEY_MOVIES = "key_movies";
    private RecyclerView rvCategory;

    public FavMovieFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvCategory = view.findViewById(R.id.rv_list);

        initViews();
        if (savedInstanceState == null) {
            getFav();
        } else {
            movies = savedInstanceState.getParcelableArrayList(KEY_MOVIES);
            generateMovieList(movies);
        }
    }

    private void initViews() {
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void getFav(){
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                srcDB = DatabaseClient.getInstance(getContext()).getAppDatabase().movieDao().getAllMovie("Movie");
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
                Log.d("Get Fav", "onError: " + movies);
            }
        });
    }

    private void generateMovieList(ArrayList<Movie> movies) {
        MovieAdapter adapter = new MovieAdapter(getContext());
        adapter.setListMovie(movies);
        rvCategory.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        initViews();
        getFav();
    }
}
