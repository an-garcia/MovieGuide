package com.xengar.android.movieguide.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xengar.android.movieguide.data.FavoriteMoviesContract.FavoriteMovieColumn;

/**
 * Database helper for FavoriteMovies. Manages database creation and version management.
 */
public class FavoriteMoviesDbHelper extends SQLiteOpenHelper {

    // Name of the database file
    private static final String DATABASE_NAME = "FavoriteMovies.db";

    // Database version. If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 2;

    // Constructor
    public FavoriteMoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "CREATE TABLE " + FavoriteMovieColumn.TABLE_NAME + " ("
            + "_id INTEGER PRIMARY KEY, "
            + FavoriteMovieColumn.COLUMN_NAME_MOVIE_ID + " INTEGER, "
            + FavoriteMovieColumn.COLUMN_NAME_TITLE + " TEXT, "
            + FavoriteMovieColumn.COLUMN_MOVIE_PLOT + " TEXT, "
            + FavoriteMovieColumn.COLUMN_POSTER_PATH + " TEXT, "
            + FavoriteMovieColumn.COLUMN_YEAR + " TEXT, "
            + FavoriteMovieColumn.COLUMN_DURATION + " INTEGER, "
            + FavoriteMovieColumn.COLUMN_VOTE_AVERAGE + " REAL, "
            + FavoriteMovieColumn.COLUMN_BACKGROUND_PATH + " TEXT, "
            + FavoriteMovieColumn.COLUMN_ORIGINAL_LANGUAGE + " TEXT, "
            + FavoriteMovieColumn.COLUMN_STATUS + " TEXT, "
            + FavoriteMovieColumn.COLUMN_IMDB_ID + " TEXT)";

        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + FavoriteMovieColumn.TABLE_NAME;

        db.execSQL(SQL_DROP_TABLE);
        onCreate(db);
    }
}
