package com.xengar.android.movieguide.data;

import java.util.List;

/**
 * Details for a Movie
 */
public class MovieDetailsData extends MovieData {

    public final String plot;
    public final String year;
    public final Integer duration;
    public final Double voteAverage;
    public final String backgroundPath;
    public final String originalLanguage;
    public final List<String> originalCountries;
    public final List<String> genres;
    public final String status;
    public final String imdbUri;
    public final List<String> productionCompanies;

    // Constructor
    public MovieDetailsData(String moviePoster, int movieId, String title, String plot, String year,
                            Integer duration, Double voteAverage, String backgroundPath,
                            String originalLanguage, List<String> originalCountries,
                            List<String> genres, String status, String imdbUri,
                            List<String> productionCompanies) {
        super(moviePoster, movieId, title);
        this.plot = plot;
        this.year = year;
        this.duration = duration;
        this.voteAverage = voteAverage;
        this.backgroundPath = backgroundPath;
        this.originalLanguage = originalLanguage;
        this.originalCountries = originalCountries;
        this.genres = genres;
        this.status = status;
        this.imdbUri = imdbUri;
        this.productionCompanies = productionCompanies;
    }

    // Getter methods
    public String getPlot() {
        return plot;
    }

    public String getYear() {
        return year;
    }

    public Integer getDuration() {
        return duration;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public List<String> getOriginalCountries() {
        return originalCountries;
    }

    public List<String> getGenres() { return genres; }

    public String getStatus() { return status; }

    public String getImdbUri() { return imdbUri; }

    public List<String> getProductionCompanies() {
        return productionCompanies;
    }

}