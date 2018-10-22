package com.example.auzan.cataloguemovieuiux.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.auzan.cataloguemovieuiux.listener.CustomOnItemClickListener;
import com.example.auzan.cataloguemovieuiux.activity.DetailMovieActivity;
import com.example.auzan.cataloguemovieuiux.model.MovieItem;
import com.example.auzan.cataloguemovieuiux.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.auzan.cataloguemovieuiux.BuildConfig.IMG_W185_URL;

public class CardMovieAdapter extends RecyclerView.Adapter<CardMovieAdapter.ViewHolder> {

    private ArrayList<MovieItem> movieLists;
    private Context context;

    public CardMovieAdapter(Context context) {
        this.context = context;
    }


    public void setListMovie(ArrayList<MovieItem> movieLists) {
        this.movieLists = movieLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // this method will be called whenever our ViewHolder is created
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cardview_movie, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        // this method will bind the data to the ViewHolder from hence it'll be shown to other Views
        final MovieItem movList = movieLists.get(position);

        holder.title.setText(movList.getTitle());
        holder.overview.setText(movList.getOverview());
        holder.date.setText(movList.getReleaseDate());

        String release_date = movList.getReleaseDate();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(release_date);

            SimpleDateFormat new_date_format = new SimpleDateFormat("E, MMM dd, yyyy");
            String date_of_release = new_date_format.format(date);
            holder.date.setText(date_of_release);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Picasso.get()
                .load(IMG_W185_URL + movList.getUrlGambar())
                .into(holder.poster);


        holder.btnShare.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Share: "+movList.getTitle(), Toast.LENGTH_SHORT).show();
            }
        }));

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieItem movieItem = movieLists.get(position);
                Intent Intent = new Intent(context, DetailMovieActivity.class);
                Intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movieItem);
                context.startActivity(Intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (movieLists == null) return 0;
        return movieLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {
        // deklarasi View objects
        private TextView title, overview, date;
        private ImageView poster;
        private Button btnDetail, btnShare;

        private ViewHolder(View itemView) {
            super(itemView);

            // init the View objects
            title       = itemView.findViewById(R.id.tv_title);
            poster      = itemView.findViewById(R.id.img_poster);
            overview    = itemView.findViewById(R.id.tv_overview);
            date        = itemView.findViewById(R.id.tv_release_date);
            btnDetail = itemView.findViewById(R.id.btn_detail);
            btnShare    = itemView.findViewById(R.id.btn_set_share);

        }

    }
}