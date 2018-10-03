package com.example.android.moviesappv1.Data;

public class MovieUrlConstants {

    // TODO - enter a valid API_KEY from The Movie Database in order to have code work
    final public static String API_KEY = "INSERT_YOUR_OWN_API_KEY_HERE";

    final public static String BASE_SEARCH_URL = "https://api.themoviedb.org/3/discover/movie?";

    final public static String BASE_POSTER_URL = "https://image.tmdb.org/t/p/";

    final public static String DEFAULT_POSTER_SIZE = "w185/";

    final public static String LANGUAGE_PARAM = "language";

    final public static String DEFAULT_LANGUAGE = "en-US";

    final public static String API_PARAM = "api_key";

    final public static String INCLUDE_ADULT_PARAM = "include_adult";

    final public static String INCLUDE_ADULT_DEFAULT = "false";

    final public static String INCLUDE_VIDEO_PARAM = "include_video";

    final public static String INCLUDE_VIDEO_DEFAULT = "false";

    final public static String SORT_BY_PARAM = "sort_by";

    final public static String SORT_BY_DEFAULT = "popularity.desc";

    final public static String SORT_BY_HIGHEST_RATED = "vote_average.desc";

}
