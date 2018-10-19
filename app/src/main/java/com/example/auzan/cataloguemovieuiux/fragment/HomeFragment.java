package com.example.auzan.cataloguemovieuiux.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.auzan.cataloguemovieuiux.FavoriteFragment;
import com.example.auzan.cataloguemovieuiux.R;

import static com.example.auzan.cataloguemovieuiux.BuildConfig.API_KEY;
import static com.example.auzan.cataloguemovieuiux.BuildConfig.MOVIE_URL;


public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view;

//    private static final String API_URL_NOWPLAYING = MOVIE_URL +"/now_playing?api_key="+ API_KEY +"&language=en-US";
//    private static final String API_URL_UPCOMING = MOVIE_URL+"/upcoming?api_key="+API_KEY+"&language=en-US";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new sliderAdapter(getChildFragmentManager()));

        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    private class sliderAdapter extends FragmentPagerAdapter {
        String now_playing = getResources().getString(R.string.now_playing);
        String upcoming = getResources().getString(R.string.upcoming);
        String favorite = getResources().getString(R.string.favorite);

        final String tabs[] = {now_playing, upcoming, favorite};

        public sliderAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new NowPlayingFragment();
                case 1:
                    return new UpcomingFragment();
                case 2:
                    return new FavoriteFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabs.length; //count Tabs[] size: 2
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }
}