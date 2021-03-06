package com.example.android.moviesappv1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.moviesappv1.Data.MovieUrlConstants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private List<Movies> mMovies;
    private ItemClickListener mClickListener;
    private final Context mContext;

    // data is passed into the constructor
    MovieRecyclerViewAdapter(Context context, List<Movies> movies) {
        this.mMovies = movies;
        this.mContext = context;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the image to the ImageView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(mMovies.get(position));
        Movies movie = mMovies.get(position);
        String fullPosterPath = MovieUrlConstants.BASE_POSTER_URL + MovieUrlConstants.DEFAULT_POSTER_SIZE + movie.poster_path;
        Picasso.with(mContext).load(fullPosterPath).into(holder.myImageView);

    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mMovies.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView myImageView;

        ViewHolder(View itemView) {
            super(itemView);
            myImageView = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Movies getItem(int id) {
        return mMovies.get(id);
    }

    void setMovieData (List<Movies> movies){
        mMovies = movies;
        notifyDataSetChanged();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public void clear() {
        final int size = mMovies.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mMovies.remove(0);
            }

            notifyItemRangeRemoved(0, size);
        }
    }
}
