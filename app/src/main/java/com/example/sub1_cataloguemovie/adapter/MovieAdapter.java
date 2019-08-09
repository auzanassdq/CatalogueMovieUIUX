package com.example.sub1_cataloguemovie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CategoryViewHolder> {

    private final Context context;
    private ArrayList<Movie> listMovie;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<Movie> getListMovie(){
        return listMovie;
    }

    public void setListMovie(ArrayList<Movie> listMovie){
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.tvTitle.setText(getListMovie().get(position).getName());
        holder.tvOverview.setText(getListMovie().get(position).getDesc());

        Glide.with(context)
                .load(getListMovie().get(position).getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvOverview;
        ImageView imgPhoto;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOverview = itemView.findViewById(R.id.tv_desc);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }
    }
}
