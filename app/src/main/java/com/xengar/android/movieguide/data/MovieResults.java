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
package com.xengar.android.movieguide.data;

import com.google.gson.annotations.SerializedName;

import com.xengar.android.movieguide.service.BaseResults;

import java.util.List;

/**
 * MovieResults
 */
public class MovieResults extends BaseResults{
    @SerializedName("results")
    private List<Movie> mMovies;

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }
}
