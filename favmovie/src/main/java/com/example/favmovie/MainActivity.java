package com.example.favmovie;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.favmovie.adapter.FavMovieAdapter;
import com.example.favmovie.db.DatabaseContract;

import static com.example.favmovie.db.DatabaseContract.CONTENT_URI;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {

    private FavMovieAdapter favMovieAdapter;
    ListView lvFavMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Favorite Movie");

        lvFavMovie = findViewById(R.id.lv_favorite);
        favMovieAdapter = new FavMovieAdapter(this, null, true);
        lvFavMovie.setAdapter(favMovieAdapter);

        lvFavMovie.setOnItemClickListener(this);

        new loadDataAsync().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new loadDataAsync().execute();
    }

    private class loadDataAsync extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContentResolver().query(
                    CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            );
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            favMovieAdapter.swapCursor(cursor);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Cursor cursor = (Cursor) favMovieAdapter.getItem(i);

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns._ID));
        Intent intent = new Intent(MainActivity.this, DetailFavActivity.class);
        intent.setData(Uri.parse(CONTENT_URI + "/" + id));
        startActivity(intent);
    }
}
