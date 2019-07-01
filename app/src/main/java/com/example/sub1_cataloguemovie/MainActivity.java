package com.example.sub1_cataloguemovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sub1_cataloguemovie.adapter.MovieAdapter;
import com.example.sub1_cataloguemovie.model.Movie;
import com.example.sub1_cataloguemovie.model.MovieData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String STATE_LIST = "state_list";
    private ListView listView;
    private ArrayList<Movie> list;
    private MovieAdapter adapter;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lv_list);

        list = new ArrayList<>();
        list.addAll(MovieData.getListData());

        showListView();
    }

    private void showListView() {
        adapter = new MovieAdapter(this);
        adapter.setMovies(list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                movie = list.get(i);
                Toast.makeText(MainActivity.this, movie.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_LIST, list);
    }

}
