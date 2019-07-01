package com.example.sub1_cataloguemovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sub1_cataloguemovie.model.Movie;

public class DetailActivity extends AppCompatActivity {

    final public static String EXTRA_MOVIE = "extra_movie";
    private TextView tvTitle, tvYear, tvDesc;
    private ImageView imgPhoto;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgPhoto = findViewById(R.id.img_photo);
        tvTitle = findViewById(R.id.tv_title);
        tvYear = findViewById(R.id.tv_year);
        tvDesc = findViewById(R.id.tv_desc);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        setMovie();
    }

    private void setMovie() {
        tvTitle.setText(movie.getName());
        tvYear.setText(movie.getYear());
        tvDesc.setText(movie.getDesc());

        Glide.with(this)
                .load(movie.getPhoto())
                .apply(new RequestOptions().override(140, 180))
                .into(imgPhoto);
    }
}
