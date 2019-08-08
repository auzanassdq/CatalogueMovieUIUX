package com.example.sub1_cataloguemovie;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sub1_cataloguemovie.adapter.MovieAdapter;
import com.example.sub1_cataloguemovie.model.Movie;
import com.example.sub1_cataloguemovie.model.MovieList;
import com.example.sub1_cataloguemovie.network.GetDataService;
import com.example.sub1_cataloguemovie.network.RetrofitClientInstance;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TvShowFragment extends Fragment {

    final public static String KEY_MOVIES = "key_movies";

    private ArrayList<Movie> movies = new ArrayList<>();
    private RecyclerView rvCategory;
    private ProgressBar progressBar;
    private MovieAdapter adapter;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new MovieAdapter(getContext());

        initViews(view);
        if (savedInstanceState == null) {
            loadJSON();
        } else {
            movies = savedInstanceState.getParcelableArrayList(KEY_MOVIES);
            generateMovieList();
        }
    }

    private void initViews(View view) {
        progressBar = view.findViewById(R.id.progress_bar);
        rvCategory = view.findViewById(R.id.rv_list);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void loadJSON() {
        showLoading(true);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Observable<MovieList> observable = service.getAllTv(BuildConfig.MOVIE_API, "en-US");
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MovieList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieList movieList) {
                movies = movieList.getResults();
                for (Movie movie : movies) {
                    movie.setType("Tv Show");
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getContext(), "Data tidak dapat dimuat", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                generateMovieList();
                showLoading(false);
                progressDialog.dismiss();
            }
        });
    }

    private void generateMovieList() {
        adapter.setListMovie(movies);
        rvCategory.setAdapter(adapter);
    }

    private void showLoading(boolean state) {
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_MOVIES, movies);
        super.onSaveInstanceState(outState);
    }
}