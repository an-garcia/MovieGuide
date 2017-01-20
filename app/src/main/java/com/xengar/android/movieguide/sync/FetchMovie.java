/*
 * Copyright (C) 2017 Angel Garcia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xengar.android.movieguide.sync;

import android.os.AsyncTask;
import android.util.Log;

import com.xengar.android.movieguide.adapters.ImageAdapter;
import com.xengar.android.movieguide.data.MovieData;
import com.xengar.android.movieguide.utils.JSONLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.xengar.android.movieguide.utils.Constants.NOW_PLAYING_MOVIES;
import static com.xengar.android.movieguide.utils.Constants.POPULAR_MOVIES;
import static com.xengar.android.movieguide.utils.Constants.TOP_RATED_MOVIES;
import static com.xengar.android.movieguide.utils.Constants.UPCOMING_MOVIES;

/**
 * FetchMovie items
 */
public class FetchMovie extends AsyncTask<Integer, Void, ArrayList<MovieData>> {

    private static final String TAG = FetchMovie.class.getSimpleName();
    private static final String MOVIE_TOP_RATED = "/movie/top_rated";
    private static final String MOVIE_NOW_PLAYING = "/movie/now_playing";
    private static final String MOVIE_UPCOMING = "/movie/upcoming";
    private static final String DISCOVER_MOVIE = "/discover/movie";

    private final FetchItemListener fetchMovieListener;
    private final String posterBaseUri;
    private final ImageAdapter adapter;
    private final String apiKey;
    private final String sortOrder;
    private final String itemType;
    private String requestType;

    // Constructor
    public FetchMovie(String itemType, ImageAdapter adapter,
                      FetchItemListener fetchMovieListener, String apiKey,
                      String posterBaseUri, String sortOrder) {
        this.itemType = itemType;
        this.fetchMovieListener = fetchMovieListener;
        this.posterBaseUri = posterBaseUri;
        this.adapter = adapter;
        this.apiKey = apiKey;
        this.sortOrder = sortOrder;

        // assign the category to query
        switch (itemType){
            case TOP_RATED_MOVIES:
                this.requestType = MOVIE_TOP_RATED + "?page=";
                break;
            case NOW_PLAYING_MOVIES:
                this.requestType = MOVIE_NOW_PLAYING + "?page=";
                break;
            case UPCOMING_MOVIES:
                this.requestType = MOVIE_UPCOMING + "?page=";
                break;
            case POPULAR_MOVIES:
                this.requestType = DISCOVER_MOVIE + "?sort_by=" + sortOrder + "&page=";
                break;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<MovieData> moviePosters) {
        super.onPostExecute(moviePosters);
        if (moviePosters != null) {
            for (MovieData res : moviePosters) {
                adapter.add(res);
            }
            adapter.notifyDataSetChanged();
            fetchMovieListener.onFetchCompleted();
        } else {
            fetchMovieListener.onFetchFailed();
        }
    }

    @Override
    protected ArrayList<MovieData> doInBackground(Integer... params) {
        ArrayList<MovieData> moviePosters = new ArrayList<>();
        try {
            JSONObject jObj = JSONLoader.load(requestType + params[0], apiKey);
            if (jObj == null) {
                Log.w(TAG, "Can not load the data from remote service");
                return null;
            }

            JSONArray movieArray = jObj.getJSONArray("results");
            JSONObject movie = null;
            String moviePoster = null;
            int movieId = 0;
            String movieTitle = null;
            for (int i = 0; i < movieArray.length(); i++) {
                movie = movieArray.optJSONObject(i);
                moviePoster = movie.getString("poster_path");
                movieId = movie.getInt("id");
                movieTitle = movie.getString("title");
                MovieData data = new MovieData(posterBaseUri + moviePoster, movieId, movieTitle);
                moviePosters.add(data);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error ", e);
        }
        return moviePosters;
    }
}
