package com.example.favmovie;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.favmovie.entity.MovieItem;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailFavActivity extends AppCompatActivity {

    TextView tvTitle, tvOverview, tvRelease, tvRating;
    ImageView imgPoster;

    private MovieItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fav);

        tvTitle = findViewById(R.id.tv_title);
        tvOverview = findViewById(R.id.tv_overview);
        tvRelease = findViewById(R.id.tv_release_date);
        tvRating = findViewById(R.id.tv_rating);
        imgPoster = findViewById(R.id.img_poster);

        loadData();
        setMovie();

    }


    private void loadData() {
        Uri uri = getIntent().getData();
        if (uri != null) {
            Cursor cursor = getContentResolver().query( uri
                    ,null
                    ,null
                    ,null
                    ,null
            );

            if (cursor != null) {
                if (cursor.moveToFirst()) item = new MovieItem(cursor);
                cursor.close();
            }
        }
    }

    private void setMovie(){
        if (item == null) return;

        getSupportActionBar().setTitle(item.getTitle());
        tvOverview.setText(item.getOverview());
        tvRating.setText(item.getVoteAverage());
        getImage(item);
        String release_date = item.getReleaseDate();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(release_date);

            SimpleDateFormat new_date_format = new SimpleDateFormat("E, MMM dd, yyyy");
            String date_of_release = new_date_format.format(date);
            tvRelease.setText(date_of_release);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void getImage(MovieItem movieItem){
        if (movieItem != null){
            String urlPoster = "https://image.tmdb.org/t/p/w92/"+movieItem.getUrlGambar();
            Picasso.get().load(urlPoster).into(imgPoster);
        }
    }
}
