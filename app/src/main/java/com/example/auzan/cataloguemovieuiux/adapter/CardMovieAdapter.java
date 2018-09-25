package com.example.auzan.cataloguemovieuiux.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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



public class CardMovieAdapter extends RecyclerView.Adapter<CardMovieAdapter.ViewHolder> {

    private ArrayList<MovieItem> movieLists;
    private Context context;

    public CardMovieAdapter(Context context) {
        this.context = context;
    }


    public void setListMovie(ArrayList<MovieItem> movieLists) {
        this.movieLists = movieLists;
    }

    private ArrayList<MovieItem> getListMovie() {
        return movieLists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // this method will be called whenever our ViewHolder is created
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cardview_movie, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

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

//        Glide.with(context)
//                .load("http://image.tmdb.org/t/p/w500/"+movList.getUrlGambar())
//                .into(holder.poster);

        Picasso.get()
                .load("http://image.tmdb.org/t/p/w500/"+movList.getUrlGambar())
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
        return getListMovie().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        // deklarasi View objects
        public TextView title, overview, date;
        public ImageView poster;
        public Button btnDetail, btnShare;
        public LinearLayout cvDetail; //cv = cardview

        public ViewHolder(View itemView) {
            super(itemView);

            // init the View objects
            title       = (TextView) itemView.findViewById(R.id.tv_title);
            poster      = (ImageView) itemView.findViewById(R.id.img_poster);
            overview    = (TextView) itemView.findViewById(R.id.tv_overview);
            date        = (TextView) itemView.findViewById(R.id.tv_release_date);
            btnDetail = (Button) itemView.findViewById(R.id.btn_detail);
            btnShare    = (Button) itemView.findViewById(R.id.btn_set_share);
//            cvDetail    = (LinearLayout) itemView.findViewById(R.id.cv_movie);
        }

    }
}