package com.example.android.moviesappv1;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.moviesappv1.Data.MovieUrlConstants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movies>> {

    private static final String LOG_TAG = MainActivity.class.getName();

    /**
     * Constant value for the news loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int MOVIE_LOADER_ID = 1;

    /**
     * Adapter for the list of movies
     */
    private MoviePosterAdapter mAdapter;

    private TextView mEmptyStateTextView;

    private ProgressBar mProgressBarView;

    private String mSortOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSortOrder = MovieUrlConstants.SORT_BY_DEFAULT;

        // Find a reference to the {@link GridView} in the layout
        final GridView movieGridView = (GridView) findViewById(R.id.movie_poster_grid);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty);

        mProgressBarView = (ProgressBar) findViewById(R.id.progress);

        movieGridView.setEmptyView(mEmptyStateTextView);

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            // Create a new adapter that takes an empty list of movies as input
            mAdapter = new MoviePosterAdapter(this, new ArrayList<Movies>());

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            movieGridView.setAdapter(mAdapter);

            // Set an item click listener on the GridView, which sends an intent to DetailActivity
            // with more information about the selected movie.
            movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Movies currentMovie = mAdapter.getItem(position);
                    if (currentMovie != null) {
                        launchDetailActivity(currentMovie.title, currentMovie.original_title, currentMovie.poster_path, currentMovie.plot_synopsis, currentMovie.user_rating, currentMovie.release_date);
                    }
                }

            });

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            Log.i(LOG_TAG, "TEST:  Loader initialized");
            loaderManager.initLoader(MOVIE_LOADER_ID, null, this);

        } else {
            // Set progress bar view visibility to gone
            mProgressBarView.setVisibility(View.GONE);

            // Set empty state text to display "No internet connection."
            mEmptyStateTextView.setText(R.string.no_internet);

        }


    }

    private void launchDetailActivity(String title, String originalTitle, String posterPath, String overview, String userRating, String releaseDate) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.TITLE, title);
        intent.putExtra(DetailActivity.ORIGINAL_TITLE, originalTitle);
        intent.putExtra(DetailActivity.POSTER_PATH, posterPath);
        intent.putExtra(DetailActivity.OVERVIEW, overview);
        intent.putExtra(DetailActivity.USER_RATING, userRating);
        intent.putExtra(DetailActivity.RELEASE_DATE, releaseDate);
        startActivity(intent);
    }

    @Override
    public Loader<List<Movies>> onCreateLoader(int i, Bundle bundle) {

        Uri baseUri = Uri.parse(MovieUrlConstants.BASE_SEARCH_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter(MovieUrlConstants.API_PARAM, MovieUrlConstants.API_KEY);
        uriBuilder.appendQueryParameter(MovieUrlConstants.LANGUAGE_PARAM, MovieUrlConstants.DEFAULT_LANGUAGE);
        uriBuilder.appendQueryParameter(MovieUrlConstants.INCLUDE_ADULT_PARAM, MovieUrlConstants.INCLUDE_ADULT_DEFAULT);
        uriBuilder.appendQueryParameter(MovieUrlConstants.INCLUDE_VIDEO_PARAM, MovieUrlConstants.INCLUDE_VIDEO_DEFAULT);
        uriBuilder.appendQueryParameter(MovieUrlConstants.SORT_BY_PARAM, mSortOrder);


        return new MovieLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Movies>> loader, List<Movies> movies) {
        // Clear the adapter of previous news data
        mAdapter.clear();

        // Set progress bar view visibility to gone
        mProgressBarView.setVisibility(View.GONE);

        // Set empty state text to display "No movies found."
        mEmptyStateTextView.setText("Something went wrong...\n\nNo movies found!");

        Log.i(LOG_TAG, "TEST: onLoadFinished() executed");

        // If there is a valid list of {@link News}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (movies != null && !movies.isEmpty()) {
            mAdapter.addAll(movies);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Movies>> loader) {
        Log.i(LOG_TAG, "TEST: onLoaderReset() executed");
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if (id == R.id.action_detail) {
            Intent detailIntent = new Intent(this, DetailActivity.class);
            startActivity(detailIntent);
            return true;
        }*/
        switch (id) {
            case R.id.action_most_popular:
                mSortOrder = MovieUrlConstants.SORT_BY_DEFAULT;
                getLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
                return true;
            case R.id.action_highest_rated:
                mSortOrder = MovieUrlConstants.SORT_BY_HIGHEST_RATED;
                getLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}









