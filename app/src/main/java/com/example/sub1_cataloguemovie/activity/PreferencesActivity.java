package com.example.sub1_cataloguemovie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.sub1_cataloguemovie.fragment.MyPreferenceFragment;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new MyPreferenceFragment())
                .commit();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
