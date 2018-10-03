package com.example.android.moviesappv1;

import android.os.Parcel;
import android.os.Parcelable;

public class Movies implements Parcelable {
    final String title;
    final String original_title;
    final String poster_path; // path to be appended to base url and poster size
    final String plot_synopsis;
    final String user_rating;
    final String release_date;
    private final String id;

    public Movies(String mTitle, String mOriginalTitle, String imagePath, String plot, String ratings, String mReleaseDate, String movieId)
    {
        this.title = mTitle;
        this.original_title = mOriginalTitle;
        this.poster_path = imagePath;
        this.plot_synopsis = plot;
        this.user_rating = ratings;
        this.release_date = mReleaseDate;
        this.id = movieId;
    }

    private Movies(Parcel in){
        title = in.readString();
        original_title = in.readString();
        poster_path = in.readString();
        plot_synopsis = in.readString();
        user_rating = in.readString();
        release_date = in.readString();
        id = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String toString() { return "Title: " + title + "\nOriginal Title: " + original_title + "\nPoster Path: " + poster_path + "\nPlot Synopsis: " + plot_synopsis + "\nUser Rating: " + user_rating + "\nRelease Date: " + release_date + "\nMovie ID: " + id; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(original_title);
        parcel.writeString(poster_path);
        parcel.writeString(plot_synopsis);
        parcel.writeString(user_rating);
        parcel.writeString(release_date);
        parcel.writeString(id);
    }

    public final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel parcel) {
            return new Movies(parcel);
        }

        @Override
        public Movies[] newArray(int i) {
            return new Movies[i];
        }

    };
}
