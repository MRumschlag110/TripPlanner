package com.project.tripplanner;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class TripPlannerActivity extends SingleFragmentActivity {
    private static final String TAG = "PhotoGalleryActivity";
    public static boolean isVibrating = false;
    public static boolean isAudible = false;

    @Override
    public Fragment createFragment() {
        return new TripPlannerFragment();
    }

    @Override
    public void onNewIntent(Intent intent) {
        TripPlannerFragment fragment = (TripPlannerFragment)getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainer);

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.i(TAG, "Received a new search query: " + query);

            PreferenceManager.getDefaultSharedPreferences(this)
                .edit()
                .putString(FlickrFetchr.PREF_SEARCH_QUERY, query)
                .commit();
        }

        fragment.updateItems();
    }
}
