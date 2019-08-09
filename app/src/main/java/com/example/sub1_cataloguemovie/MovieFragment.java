package com.example.sub1_cataloguemovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sub1_cataloguemovie.adapter.MovieAdapter;
import com.example.sub1_cataloguemovie.listener.ItemClickSupport;
import com.example.sub1_cataloguemovie.model.Movie;
import com.example.sub1_cataloguemovie.model.MovieData;

import java.util.ArrayList;

public class MovieFragment extends Fragment {

    private RecyclerView rvCategory;
    private ArrayList<Movie> list;
    private Movie movie;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        rvCategory = view.findViewById(R.id.rv_list);
        list = new ArrayList<>();
        list.addAll(MovieData.getListDataMovie());
        showListView();

        return view;
    }

    private void showListView() {
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        MovieAdapter adapter = new MovieAdapter(getActivity());
        adapter.setListMovie(list);
        rvCategory.setAdapter(adapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                movie = list.get(position);
                Toast.makeText(getActivity(), movie.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
                startActivity(intent);
            }
        });
    }
}