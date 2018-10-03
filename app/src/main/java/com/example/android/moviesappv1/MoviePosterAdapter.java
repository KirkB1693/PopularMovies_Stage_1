package com.example.android.moviesappv1;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.android.moviesappv1.Data.MovieUrlConstants;
import com.squareup.picasso.Picasso;

import java.util.List;


class MoviePosterAdapter extends ArrayAdapter<Movies> {
    private static final String LOG_TAG = MoviePosterAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the List is the data we want
     * to populate into the lists
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param movies         A List of Movie objects to display in a list
     */
    public MoviePosterAdapter(Activity context, List<Movies> movies) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, movies);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate.
     *                    (search online for "android view recycling" to learn more)
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Gets the Movie object from the ArrayAdapter at the appropriate position
        Movies movie = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_item, parent, false);
        }


        ImageView moviePosterView = (ImageView) convertView.findViewById(R.id.iv_movie_poster);
        String posterPath = "";
        if (movie != null) {
            posterPath = MovieUrlConstants.BASE_POSTER_URL + MovieUrlConstants.DEFAULT_POSTER_SIZE + movie.poster_path;
        }
        Picasso.with(getContext()).load(posterPath).into(moviePosterView);

        return convertView;
    }
}
