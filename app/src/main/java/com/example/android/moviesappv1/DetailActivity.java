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

    public static final String CURRENT_MOVIE = "current_movie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setupUI();
    }


    private void setupUI() {
        ImageView moviePosterIv = findViewById(R.id.iv_movie_poster_detail);
        ImageView movieBackdropIv = findViewById(R.id.iv_movie_background_detail);
        TextView mOriginalTitleTv = findViewById(R.id.tv_original_title);
        TextView mPlotSynopsisTv = findViewById(R.id.tv_plot_synopsis);
        TextView mUserRatingTv = findViewById(R.id.tv_user_rating);
        TextView mReleaseDateTv = findViewById(R.id.tv_release_date);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        } else {
            Movies currentMovie = intent.getParcelableExtra(CURRENT_MOVIE);

            mOriginalTitleTv.setText(currentMovie.original_title);
            mPlotSynopsisTv.setText(currentMovie.plot_synopsis);
            mUserRatingTv.setText(currentMovie.user_rating);
            mReleaseDateTv.setText(currentMovie.release_date);

            String fullPosterPath = MovieUrlConstants.BASE_POSTER_URL + MovieUrlConstants.DEFAULT_POSTER_SIZE + currentMovie.poster_path;
            Picasso.with(this).load(fullPosterPath).into(moviePosterIv);

            String fullBackdropPath = MovieUrlConstants.BASE_POSTER_URL + MovieUrlConstants.DEFAULT_BACKDROP_SIZE + currentMovie.backdrop_path;
            Picasso.with(this).load(fullBackdropPath).into(movieBackdropIv);

            if (currentMovie.title != null) {
                setTitle(currentMovie.title);
            } else {
                setTitle(R.string.app_name);
            }
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.close_on_error, Toast.LENGTH_SHORT).show();
    }
}

