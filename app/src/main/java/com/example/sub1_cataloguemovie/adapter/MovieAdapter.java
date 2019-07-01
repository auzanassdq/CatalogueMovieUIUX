package com.example.sub1_cataloguemovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sub1_cataloguemovie.R;
import com.example.sub1_cataloguemovie.model.Movie;

import java.util.ArrayList;

/**
 * Created by auzan on 6/29/2019.
 * Github: @auzanassdq
 */
public class MovieAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context){
        this.context = context;
        movies = new ArrayList<>();
    }

    public void setMovies(ArrayList<Movie> movies){
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        }

        Movie movie = (Movie) getItem(i);
        TextView tvName = view.findViewById(R.id.tv_title);
        TextView tvDesc = view.findViewById(R.id.tv_desc);
        ImageView imgPhoto = view.findViewById(R.id.img_photo);

        tvName.setText(movie.getName());
        tvDesc.setText(movie.getDesc());
        Glide.with(context)
                .load(movie.getPhoto())
                .apply(new RequestOptions().override(100, 120))
                .into(imgPhoto);

        return view;
    }
}
