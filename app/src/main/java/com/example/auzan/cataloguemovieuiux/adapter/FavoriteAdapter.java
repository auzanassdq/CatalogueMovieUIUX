package com.example.auzan.cataloguemovieuiux.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.auzan.cataloguemovieuiux.R;
import com.example.auzan.cataloguemovieuiux.activity.DetailMovieActivity;
import com.example.auzan.cataloguemovieuiux.listener.CustomOnItemClickListener;
import com.example.auzan.cataloguemovieuiux.model.MovieItem;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.auzan.cataloguemovieuiux.BuildConfig.IMG_W185_URL;

/**
 * Created by auzan on 10/15/2018.
 * Github: @auzanassdq
 */
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private Context context;
    private Cursor listFavorite;

    public FavoriteAdapter(Context context){
        this.context = context;
    }

    public void replaceAll(Cursor items) {
        this.listFavorite = items;
        notifyDataSetChanged();
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

        final MovieItem movieItem = getItem(position);

        holder.title.setText(movieItem.getTitle());
        holder.overview.setText(movieItem.getOverview());
        holder.date.setText(movieItem.getReleaseDate());

        String release_date = movieItem.getReleaseDate();
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
                .load( IMG_W185_URL + movieItem.getUrlGambar())
                .into(holder.poster);


        holder.btnShare.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Share: "+movieItem.getTitle(), Toast.LENGTH_SHORT).show();
            }
        }));

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieItem movieItem = getItem(position);
                Intent Intent = new Intent(context, DetailMovieActivity.class);
                Intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movieItem);
                context.startActivity(Intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listFavorite == null) return 0;
        return listFavorite.getCount();
    }

    private MovieItem getItem(int position){
        if (!listFavorite.moveToPosition(position)){
            throw new IllegalStateException("Position invalid");
        }
        return new MovieItem(listFavorite);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, overview, date;
        private ImageView poster;
        private Button btnDetail, btnShare;

        private ViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.tv_title);
            overview = view.findViewById(R.id.tv_overview);
            date = view.findViewById(R.id.tv_release_date);
            poster = view.findViewById(R.id.img_poster);
            btnDetail = view.findViewById(R.id.btn_detail);
            btnShare = view.findViewById(R.id.btn_set_share);
        }
    }
}
