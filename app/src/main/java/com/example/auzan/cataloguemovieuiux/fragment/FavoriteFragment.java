package com.example.auzan.cataloguemovieuiux.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.auzan.cataloguemovieuiux.R;
import com.example.auzan.cataloguemovieuiux.adapter.FavoriteAdapter;

import static com.example.auzan.cataloguemovieuiux.db.DatabaseContract.CONTENT_URI;

public class FavoriteFragment extends Fragment {

    private Context context;
    private RecyclerView rvFavorite;

    private FavoriteAdapter adapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        context = view.getContext();
        rvFavorite = view.findViewById(R.id.rv_favorite);

        adapter = new FavoriteAdapter(context);
        rvFavorite.setLayoutManager(new LinearLayoutManager(context));
        rvFavorite.setAdapter(adapter);

        new loadDataAsync().execute();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        new loadDataAsync().execute();
    }

    private class loadDataAsync extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return context.getContentResolver().query(
                    CONTENT_URI
                    , null
                    , null
                    , null
                    , null
            );
        }

        @Override
        protected void onPostExecute(Cursor favMovie) {
            super.onPostExecute(favMovie);

            adapter.replaceAll(favMovie);

            if (favMovie.getCount() == 0){
                showSnackbarMessage(getString(R.string.no_data));
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void showSnackbarMessage(String message){
        Snackbar.make(rvFavorite, message, Snackbar.LENGTH_SHORT).show();
    }
}
