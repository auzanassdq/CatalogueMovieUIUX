package com.example.sub1_cataloguemovie.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sub1_cataloguemovie.BuildConfig;
import com.example.sub1_cataloguemovie.R;
import com.example.sub1_cataloguemovie.Utils;
import com.example.sub1_cataloguemovie.db.DatabaseClient;
import com.example.sub1_cataloguemovie.model.Movie;
import com.squareup.picasso.Picasso;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class DetailActivity extends AppCompatActivity {

    final public static String EXTRA_MOVIE = "extra_movie";
    final public static String MOVIE_STATE = "movie_state";
    private TextView tvRating, tvYear, tvDesc;
    private ImageView imgPoster, imgBackdrop;
    private FloatingActionButton fbFavorite;
    private CollapsingToolbarLayout colapseBar;
    private Movie movie;
    private Movie movHelper;

    private Boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();

        if (savedInstanceState != null) {
            movie = savedInstanceState.getParcelable(MOVIE_STATE);
        } else {
            movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        }

        setMovie();

        fbFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite) {
                    favoriteRemove();
                } else {
                    favoriteSave();
                }

                isFavorite = !isFavorite;
                favoriteSet();
            }
        });
    }

    private void initView() {
        imgPoster = findViewById(R.id.img_poster);
        imgBackdrop = findViewById(R.id.img_backdrop);
        tvRating = findViewById(R.id.tv_rating);
        tvYear = findViewById(R.id.tv_year);
        tvDesc = findViewById(R.id.tv_overview);
        colapseBar = findViewById(R.id.colappsingtoolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        fbFavorite = findViewById(R.id.fb_favorite);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void favoriteRemove(){
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().movieDao().deleteProvider(movie.getId());
            }

        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                Log.d("Remove Fav", "onComplete: " + movie);
                Toast.makeText(getApplicationContext(), "Remove from favorite", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Remove Fav", "onError: " + movie);
            }
        });
    }

    private void favoriteSave(){
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().movieDao().insertProvider(movie);
            }

        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                Log.d("Save Fav", "onComplete: " + movie);
                Toast.makeText(getApplicationContext(), "Saved to favorite", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Save Fav", "onError: " + movie);
            }
        });
    }

    private void loadRoom(){
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                movHelper = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().movieDao().getMovie(movie.getId());

                if (movHelper != null){
                    isFavorite = true;
                }
                favoriteSet();
            }

        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                Log.d("Load Room", "onComplete: " + movHelper);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Load Room", "onError: " + movHelper);
            }
        });
    }

    public void favoriteSet(){
        if (isFavorite) {
            fbFavorite.setImageResource(R.drawable.ic_favorite_white);
        } else {
            fbFavorite.setImageResource(R.drawable.ic_favorite_border_white);
        }
    }

    private void setMovie() {
        loadRoom();

        colapseBar.setTitle(movie.getTitle());

        String release_date = movie.getDate();
        tvYear.setText(Utils.getConvertDate(release_date));

        tvRating.setText(movie.getRating());
        tvDesc.setText(movie.getOverview());
        setImage(movie);
        favoriteSet();
    }

    private void setImage(Movie movie) {
        if (movie.getPoster() != null){
            String urlPoster = BuildConfig.MOVIE_W185 + movie.getPoster();
            Picasso.get().load(urlPoster).into(imgPoster);
        }
        if (movie.getBackdrop() != null){
            String urlBackdrop = BuildConfig.MOVIE_W500 + movie.getBackdrop();
            Picasso.get().load(urlBackdrop).into(imgBackdrop);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MOVIE_STATE, movie);
    }


}
