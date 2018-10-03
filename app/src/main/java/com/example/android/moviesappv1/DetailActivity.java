package com.example.android.moviesappv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moviesappv1.Data.MovieUrlConstants;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public static final String TITLE = "title";
    public static final String ORIGINAL_TITLE = "original_title";
    public static final String POSTER_PATH = "poster_path";
    public static final String OVERVIEW = "overview";
    public static final String USER_RATING = "user_rating";
    public static final String RELEASE_DATE = "release_date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView moviePosterIv = findViewById(R.id.iv_movie_poster_detail);
        TextView mOriginalTitleTv = findViewById(R.id.tv_original_title);
        TextView mPlotSynopsisTv = findViewById(R.id.tv_plot_synopsis);
        TextView mUserRatingTv = findViewById(R.id.tv_user_rating);
        TextView mReleaseDateTv = findViewById(R.id.tv_release_date);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        assert intent != null;
        String title = intent.getStringExtra(TITLE);
        String originalTitle = intent.getStringExtra(ORIGINAL_TITLE);
        String posterPath = intent.getStringExtra(POSTER_PATH);
        String overview = intent.getStringExtra(OVERVIEW);
        String userRating = intent.getStringExtra(USER_RATING);
        String releaseDate = intent.getStringExtra(RELEASE_DATE);


        mOriginalTitleTv.setText(originalTitle);
        mPlotSynopsisTv.setText(overview);
        mUserRatingTv.setText(userRating);
        mReleaseDateTv.setText(releaseDate);

        String fullPosterPath = MovieUrlConstants.BASE_POSTER_URL + MovieUrlConstants.DEFAULT_POSTER_SIZE + posterPath;
        Picasso.with(this).load(fullPosterPath).into(moviePosterIv);

        if (title != null) {
            setTitle(title);
        } else {
            setTitle(R.string.app_name);
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, "Something went wrong... ", Toast.LENGTH_SHORT).show();
    }


}

