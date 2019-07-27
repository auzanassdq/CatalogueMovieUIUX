package com.example.sub1_cataloguemovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sub1_cataloguemovie.model.Movie;
import com.squareup.picasso.Picasso;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

public class DetailActivity extends AppCompatActivity {

    final public static String EXTRA_MOVIE = "extra_movie";
    final public static String MOVIE_STATE = "movie_state";
    private TextView tvRating, tvYear, tvDesc;
    private ImageView imgPoster, imgBackdrop;
    private CollapsingToolbarLayout colapseBar;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgPoster = findViewById(R.id.img_poster);
        imgBackdrop = findViewById(R.id.img_backdrop);
        tvRating = findViewById(R.id.tv_rating);
        tvYear = findViewById(R.id.tv_year);
        tvDesc = findViewById(R.id.tv_overview);
        colapseBar = findViewById(R.id.colappsingtoolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            movie = savedInstanceState.getParcelable(MOVIE_STATE);
        } else {
            movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        }
        setMovie();
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

    private void setMovie() {
        colapseBar.setTitle(movie.getTitle());

        String release_date = movie.getDate();
        tvYear.setText(Utils.getConvertDate(release_date));

        tvRating.setText(String.valueOf(movie.getRating()));
        tvDesc.setText(movie.getOverview());
        setImage(movie);
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

}
