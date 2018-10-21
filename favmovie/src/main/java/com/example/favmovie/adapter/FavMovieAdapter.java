package com.example.favmovie.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.favmovie.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.favmovie.db.DatabaseContract.FavoriteColumns.OVERVIEW;
import static com.example.favmovie.db.DatabaseContract.FavoriteColumns.POSTER;
import static com.example.favmovie.db.DatabaseContract.FavoriteColumns.RELEASE;
import static com.example.favmovie.db.DatabaseContract.FavoriteColumns.TITLE;
import static com.example.favmovie.db.DatabaseContract.getColumnString;

/**
 * Created by auzan on 10/14/2018.
 * Github: @auzanassdq
 */
public class FavMovieAdapter extends CursorAdapter {

    public FavMovieAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fav_movie, parent, false);
        return view;
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor != null) {
            TextView tvTitle = (TextView)view.findViewById(R.id.tv_title);
            TextView tvOverview = (TextView)view.findViewById(R.id.tv_overview);
            TextView tvDate = (TextView)view.findViewById(R.id.tv_release_date);
            ImageView imgPoster = (ImageView)view.findViewById(R.id.img_poster);

            tvTitle.setText(getColumnString(cursor, TITLE));
            tvOverview.setText(getColumnString(cursor, OVERVIEW));
            tvDate.setText(getColumnString(cursor, RELEASE));

            String release_date = getColumnString(cursor, RELEASE);
            SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = date_format.parse(release_date);

                SimpleDateFormat new_date_format = new SimpleDateFormat("E, MMM dd, yyyy");
                String date_of_release = new_date_format.format(date);
                tvDate.setText(date_of_release);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Picasso.get()
                    .load("http://image.tmdb.org/t/p/w500/"+ getColumnString(cursor, POSTER))
                    .into(imgPoster);
        }
    }

}
