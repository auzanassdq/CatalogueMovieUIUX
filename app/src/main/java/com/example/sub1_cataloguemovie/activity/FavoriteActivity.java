package com.example.sub1_cataloguemovie.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.sub1_cataloguemovie.fragment.FavMovieFragment;
import com.example.sub1_cataloguemovie.fragment.FavTvShowFragment;
import com.example.sub1_cataloguemovie.R;
import com.example.sub1_cataloguemovie.adapter.ViewPagerAdapter;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Favorite");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FavMovieFragment(), getResources().getString(R.string.movies));
        adapter.addFragment(new FavTvShowFragment(), getResources().getString(R.string.tv_shows));
        viewPager.setAdapter(adapter);
    }
}