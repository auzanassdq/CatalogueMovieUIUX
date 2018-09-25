package com.example.auzan.cataloguemovieuiux.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.auzan.cataloguemovieuiux.model.MovieItem;
import com.example.auzan.cataloguemovieuiux.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.CategoryViewHolder> {

    private ArrayList<MovieItem> movieLists;
    private Context context;

    public ListMovieAdapter(Context context) {
        this.context = context;
    }

    public void setListMovie(ArrayList<MovieItem> movieLists) {
        this.movieLists = movieLists;
    }

    private ArrayList<MovieItem> getListMovie() {
        return movieLists;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_movie, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {
        final MovieItem movList = movieLists.get(position);

        holder.tvTitle.setText(movList.getTitle());
        holder.tvOverview.setText(movList.getOverview());

        Picasso.get()
                .load("http://image.tmdb.org/t/p/w500/"+movList.getUrlGambar())
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvOverview;
        ImageView imgPoster;
        RecyclerView rvList;
        LinearLayout view_container;

        CategoryViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvOverview = (TextView) itemView.findViewById(R.id.tv_overview);
            imgPoster = (ImageView) itemView.findViewById(R.id.img_poster);
            rvList = (RecyclerView) itemView.findViewById(R.id.rv_category);
        }
    }
}
