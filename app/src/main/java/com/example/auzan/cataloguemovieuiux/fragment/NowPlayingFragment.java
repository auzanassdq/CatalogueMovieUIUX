package com.example.auzan.cataloguemovieuiux.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.auzan.cataloguemovieuiux.R;
import com.example.auzan.cataloguemovieuiux.adapter.CardMovieAdapter;
import com.example.auzan.cataloguemovieuiux.model.MovieItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.auzan.cataloguemovieuiux.BuildConfig.API_KEY;
import static com.example.auzan.cataloguemovieuiux.BuildConfig.EN_LANG_URL;
import static com.example.auzan.cataloguemovieuiux.BuildConfig.MOVIE_URL;
import static com.example.auzan.cataloguemovieuiux.BuildConfig.NOW_PLAYING_URL;

public class NowPlayingFragment extends Fragment {

    private RecyclerView rvCategory;
    private ArrayList<MovieItem> movieLists;
    private View view;

    private static final String API_URL = MOVIE_URL + NOW_PLAYING_URL + API_KEY + EN_LANG_URL;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_now_playing, container,false);

        rvCategory = view.findViewById(R.id.rv_list);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieLists = new ArrayList<>();

        loadUrlData();

        return view;
    }

    private void loadUrlData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++){

                        JSONObject movie = array.getJSONObject(i);
                        MovieItem movies = new MovieItem(movie);
                        movies.setReleaseDate(movie.getString("release_date"));
                        movieLists.add(movies);

                    }
                    showRecyclerCardView();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                loadUrlData();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void showRecyclerCardView() {
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        CardMovieAdapter cardAdapter = new CardMovieAdapter(getActivity());
        cardAdapter.setListMovie(movieLists);
        rvCategory.setAdapter(cardAdapter);
    }
}
