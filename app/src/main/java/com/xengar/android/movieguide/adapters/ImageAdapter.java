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
package com.xengar.android.movieguide.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xengar.android.movieguide.R;
import com.xengar.android.movieguide.data.MovieData;
import com.xengar.android.movieguide.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Image adapter used to fill the list of Movie posters.
 */
public class ImageAdapter extends BaseAdapter {

    private static final String TAG = ImageAdapter.class.getSimpleName();
    private static final int IMAGE_WIDTH = 185;
    private static final int IMAGE_HEIGHT = 278;
    private final ArrayList<MovieData> finalMoviePosters = new ArrayList<>();
    private final HashSet<Integer> movieIdSet = new HashSet<>();
    private final float density;
    private final Context mContext;

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
        density = mContext.getResources().getDisplayMetrics().density;
    }

    @Override
    public int getCount() {
        return finalMoviePosters.size();
    }

    @Override
    public Object getItem(int position) {
        return finalMoviePosters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return finalMoviePosters.get(position).getMovieId();
    }

    // Creates a new ImageView for each item referenced by the Adapter.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.image_layout, parent, false);
        } else {
            view = convertView;
        }

        MovieData data = finalMoviePosters.get(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.movie_poster_view);
        view.setLayoutParams(new GridView.LayoutParams((int) (IMAGE_WIDTH * density),
                (int) (IMAGE_HEIGHT * density)));

        if (data.getMoviePoster() == null) {
            TextView textView = (TextView) view.findViewById(R.id.movie_title);
            textView.setText(data.getMovieTitle());
        }

        ActivityUtils.loadImage(mContext, data.getMoviePoster(), false, 0, imageView, null);
        return view;
    }


    public void add(MovieData res) {
        if (movieIdSet.contains(res.getMovieId())) {
            Log.w(TAG, "Movie duplicate found, movieID = " + res.getMovieId());
            return;
        }
        finalMoviePosters.add(res);
        movieIdSet.add(res.getMovieId());
    }

    public void addAll(List<MovieData> res) {
        finalMoviePosters.addAll(res);
    }

    public void clearData() {
        finalMoviePosters.clear();
        movieIdSet.clear();
        notifyDataSetChanged();
    }

    public List<MovieData> getMovieData() {
        return finalMoviePosters;
    }
}
