package com.example.auzan.cataloguemovieuiux.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.auzan.cataloguemovieuiux.fragment.MyPreferenceFragment;

public class SettingActivity extends AppCompatActivity {

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
