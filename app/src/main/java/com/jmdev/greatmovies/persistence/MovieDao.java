package com.jmdev.greatmovies.persistence;


import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.jmdev.greatmovies.test.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = IGNORE)
    long[] insertAll(Movie... movies);

    @Insert(onConflict = IGNORE)
    long[] insertAll(List<Movie> movies);

    @Query("SELECT * FROM movies")
    LiveData<List<Movie>>getMoves();

    /*@Query("SELECT * FROM movies")
    LiveData<List<Movie>>getMovies();*/




}
