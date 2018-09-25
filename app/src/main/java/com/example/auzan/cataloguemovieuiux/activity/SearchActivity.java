package com.example.auzan.cataloguemovieuiux.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.auzan.cataloguemovieuiux.R;
import com.example.auzan.cataloguemovieuiux.adapter.ListMovieAdapter;
import com.example.auzan.cataloguemovieuiux.listener.ItemClickSupport;
import com.example.auzan.cataloguemovieuiux.model.MovieItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.auzan.cataloguemovieuiux.BuildConfig.API_KEY;
import static com.example.auzan.cataloguemovieuiux.BuildConfig.MOVIE_URL_SEARCH;

public class SearchActivity extends AppCompatActivity {

    private EditText edtSearch;
    private ImageButton btnClear;
    private Button btnSearch;

    private RecyclerView rvCategory;
    private RecyclerView.Adapter adapter;
    private ArrayList<MovieItem> movieLists;
    private Context context;
    private View view;

    private String API_URL =  MOVIE_URL_SEARCH + "?api_key=" + API_KEY + "&language=en-US&query=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edtSearch = (EditText)findViewById(R.id.edt_search);
        btnClear = (ImageButton)findViewById(R.id.btn_clear);
        btnSearch = (Button) findViewById(R.id.btn_search);

        rvCategory = (RecyclerView)findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        movieLists = new ArrayList<>();

        btnSearch.setOnClickListener(search);
        btnClear.setOnClickListener(clear);

    }

    private void loadUrlData() {
        String mJudulMovie = edtSearch.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        API_URL =  API_URL + mJudulMovie;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++){
                        MovieItem movies = new MovieItem(jsonObject);

                        JSONObject data = array.getJSONObject(i);
                        movies.setTitle(data.getString("title"));
                        movies.setOverview(data.getString("overview"));
                        movies.setReleaseDate(data.getString("release_date"));
                        movies.setUrlGambar(data.getString("poster_path"));
                        movies.setBackdrop(data.getString("backdrop_path"));
                        movies.setVoteAverage(data.getString("vote_average"));
                        movieLists.add(movies);
                    }
//                    ListMovieAdapter adapter = new ListMovieAdapter (context);
//                    rvCategory.setAdapter(adapter);
                    showRecyclerList();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                loadUrlData();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showSelectedMovie(MovieItem movieItem) {
        Intent intent = new Intent(SearchActivity.this, DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movieItem);
        startActivity(intent);
    }

    private void showRecyclerList() {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(this);
        listMovieAdapter.setListMovie(movieLists);
        rvCategory.setAdapter(listMovieAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedMovie(movieLists.get(position));
            }
        });
    }


    View.OnClickListener search = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loadUrlData();
        }
    };

    View.OnClickListener clear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            edtSearch.getText().clear();
            edtSearch.clearFocus();
        }
    };
}
