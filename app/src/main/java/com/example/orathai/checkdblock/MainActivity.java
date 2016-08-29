package com.example.orathai.checkdblock;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {

    private android.app.FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RssFragment rssFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            addRssFragment();
        }
    }

    private void addRssFragment() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        rssFragment = new RssFragment();
        fragmentTransaction.add(R.id.fragment_container, rssFragment);
        fragmentTransaction.commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("fragment_added", true);
    }
}
