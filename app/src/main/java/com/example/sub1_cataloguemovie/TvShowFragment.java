package com.example.sub1_cataloguemovie;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sub1_cataloguemovie.adapter.MovieAdapter;
import com.example.sub1_cataloguemovie.model.Movie;
import com.example.sub1_cataloguemovie.model.MovieList;
import com.example.sub1_cataloguemovie.network.GetDataService;
import com.example.sub1_cataloguemovie.network.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowFragment extends Fragment {

    private RecyclerView rvCategory;
    private ProgressBar progressBar;

    public TvShowFragment() {
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
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);

        initViews(view);
        return view;
    }

    private void initViews(View view) {
        progressBar = view.findViewById(R.id.progress_bar);
        rvCategory = view.findViewById(R.id.rv_list);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadJSON();
    }

    private void loadJSON() {
        showLoading(true);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<MovieList> call = service.getAllTv(BuildConfig.MOVIE_API, "en-US");
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(@NonNull Call<MovieList> call, @NonNull Response<MovieList> response) {
                if (response.body() != null) {
                    generateMovieList(response.body().getResults());
                    showLoading(false);
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieList> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Tidak dapat memuat data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateMovieList(final ArrayList<Movie> movies) {
        MovieAdapter adapter = new MovieAdapter(getContext());
        adapter.setListMovie(movies);
        rvCategory.setAdapter(adapter);
    }

    private void showLoading(boolean state) {
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}