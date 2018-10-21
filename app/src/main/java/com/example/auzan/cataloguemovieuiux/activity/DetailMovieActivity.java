package com.example.auzan.cataloguemovieuiux.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.auzan.cataloguemovieuiux.R;
import com.example.auzan.cataloguemovieuiux.db.MovieHelper;
import com.example.auzan.cataloguemovieuiux.model.MovieItem;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.BaseColumns._ID;
import static com.example.auzan.cataloguemovieuiux.BuildConfig.IMG_W185_URL;
import static com.example.auzan.cataloguemovieuiux.BuildConfig.IMG_W500_URL;
import static com.example.auzan.cataloguemovieuiux.db.DatabaseContract.CONTENT_URI;
import static com.example.auzan.cataloguemovieuiux.db.DatabaseContract.FavoriteColumns.BACKDROP;
import static com.example.auzan.cataloguemovieuiux.db.DatabaseContract.FavoriteColumns.OVERVIEW;
import static com.example.auzan.cataloguemovieuiux.db.DatabaseContract.FavoriteColumns.POSTER;
import static com.example.auzan.cataloguemovieuiux.db.DatabaseContract.FavoriteColumns.RATING;
import static com.example.auzan.cataloguemovieuiux.db.DatabaseContract.FavoriteColumns.RELEASE;
import static com.example.auzan.cataloguemovieuiux.db.DatabaseContract.FavoriteColumns.TITLE;

public class DetailMovieActivity extends AppCompatActivity {

    final public static String EXTRA_MOVIE = "extra_movie";

    private TextView tvOverview, tvRating, tvReleaseDate;
    private CollapsingToolbarLayout colapseBar;
    private ImageView imgPoster, imgBackdrop;
    private FloatingActionButton fabFavorite;

    private MovieItem movieItem;
    private MovieHelper movieHelper;
    private Boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tvOverview = findViewById(R.id.tv_overview);
        tvRating = findViewById(R.id.tv_rating);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        imgPoster = findViewById(R.id.img_poster);
        imgBackdrop = findViewById(R.id.img_backdrop);
        fabFavorite = findViewById(R.id.fv_favorite);
        colapseBar = findViewById(R.id.colappsingtoolbar);

        movieItem = getIntent().getParcelableExtra(EXTRA_MOVIE);

        setMovie();

        fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite) {
                    FavoriteRemove();
                } else {
                    FavoriteSave();
                }

                isFavorite = !isFavorite;
                favoriteSet();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (movieHelper != null) movieHelper.close();
    }

    private void favoriteSet() {
        if (isFavorite) {
            fabFavorite.setImageResource(R.drawable.ic_favorite_black);
        } else {
            fabFavorite.setImageResource(R.drawable.ic_favorite_border_black);
        }
    }

    private void FavoriteSave() {
        ContentValues values = new ContentValues();

        values.put(_ID, movieItem.getId());
        values.put(TITLE, movieItem.getTitle());
        values.put(OVERVIEW, movieItem.getOverview());
        values.put(RELEASE, movieItem.getReleaseDate());
        values.put(RATING, movieItem.getVoteAverage());
        values.put(POSTER, movieItem.getUrlGambar());
        values.put(BACKDROP, movieItem.getBackdrop());

        getContentResolver().insert(CONTENT_URI, values);
        Toast.makeText(this, "Add to your Favorite", Toast.LENGTH_SHORT).show();
    }

    private void FavoriteRemove() {
        getContentResolver().delete(
            Uri.parse(CONTENT_URI + "/" + movieItem.getId())
                , null
                , null);

        Toast.makeText(this, "Telah dihapus dari Favorite", Toast.LENGTH_SHORT).show();
    }

    private void setMovie(){
        loadDataSQLite();

        colapseBar.setTitle(movieItem.getTitle());
        tvOverview.setText(movieItem.getOverview());
        tvRating.setText(movieItem.getVoteAverage());
        getImage(movieItem);
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
            String urlPoster = IMG_W185_URL + movieItem.getUrlGambar();
            Picasso.get().load(urlPoster).into(imgPoster);
        }
        if (movieItem.getUrlGambar() != null){
            String urlBackdrop = IMG_W500_URL + movieItem.getBackdrop();
            Picasso.get().load(urlBackdrop).into(imgBackdrop);
        }
    }

    private void loadDataSQLite() {
        movieHelper = new MovieHelper(this);
        movieHelper.open();

        Cursor cursor = getContentResolver().query(
                Uri.parse(CONTENT_URI + "/" + movieItem.getId())
                , null
                ,null
                ,null
                ,null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) isFavorite = true;
            cursor.close();
        }
        favoriteSet();
    }

}
