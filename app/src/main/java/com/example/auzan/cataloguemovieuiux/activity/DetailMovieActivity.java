package com.example.auzan.cataloguemovieuiux.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.auzan.cataloguemovieuiux.R;
import com.example.auzan.cataloguemovieuiux.model.MovieItem;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity {

    final public static String EXTRA_MOVIE = "extra_movie";

    private TextView tvTitle, tvOverview, tvRating, tvReleaseDate;
    private CollapsingToolbarLayout colapseBar;
    private ImageView imgPoster, imgBackdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvOverview = (TextView)findViewById(R.id.tv_overview);
        tvRating = (TextView)findViewById(R.id.tv_rating);
        tvReleaseDate = (TextView)findViewById(R.id.tv_release_date);
        imgPoster = (ImageView)findViewById(R.id.img_poster);
        imgBackdrop = (ImageView) findViewById(R.id.img_backdrop);
        colapseBar = (CollapsingToolbarLayout) findViewById(R.id.colappsingtoolbar);

        MovieItem movieItem = getIntent().getParcelableExtra(EXTRA_MOVIE);
        getImage(movieItem);

        colapseBar.setTitle(movieItem.getTitle());
        tvOverview.setText(movieItem.getOverview());
        tvRating.setText(movieItem.getVoteAverage());

        String release_date = movieItem.getReleaseDate();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(release_date);

            SimpleDateFormat new_date_format = new SimpleDateFormat("E, MMM dd, yyyy");
            String date_of_release = new_date_format.format(date);
            tvReleaseDate.setText(date_of_release);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void getImage(MovieItem movieItem){
        if (movieItem.getUrlGambar() != null){
            String urlPoster = "https://image.tmdb.org/t/p/w92/"+movieItem.getUrlGambar();
            Picasso.get().load(urlPoster).into(imgPoster);
        }
        if (movieItem.getUrlGambar() != null){
            String urlBackdrop = "https://image.tmdb.org/t/p/w300/"+movieItem.getBackdrop();
            Picasso.get().load(urlBackdrop).into(imgBackdrop);
        }
    }
}
