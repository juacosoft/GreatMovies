package com.jmdev.greatmovies.repository;

import android.content.Context;

import com.jmdev.greatmovies.test.model.MovieDBResponse;
import com.jmdev.greatmovies.persistence.MovieDao;
import com.jmdev.greatmovies.persistence.MovieDatabase;

import retrofit2.Call;

public class MoviesRepository {

    private static MoviesRepository instance;
    private MovieDao movieDao;
    private Call<MovieDBResponse> apiCall;

    public static MoviesRepository getInstance(Context context) {
        if (instance == null) {
            instance = new MoviesRepository(context);
        }
        return instance;
    }

    private MoviesRepository(Context context) {
        movieDao = MovieDatabase.getInstance(context).getMovieDao();
    }


    /*public LiveData<List<Movie>> fetchMovies() {
        return new NetworkBoundResource<List<Movie>, MovieDBResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull MovieDBResponse item) {
                if (item.getMovies() != null) { // recipe list will be null if the api key is expired
//                    Log.d(TAG, "saveCallResult: recipe response: " + item.toString());

                    Movie[] movies = new Movie[item.getMovies().size()];

                    int index = 0;
                    for (long rowid : movieDao.insertAll(movies)) {
                        if (rowid == -1) {
                            Log.d("SaveError", "saveCallResult: CONFLICT... This recipe is already in the cache");

                        }
                        Log.d("SaveSucces", "Save Successfull");
                        index++;
                    }
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Movie> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Movie>> loadFromDb() {
                return movieDao.getMoves();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieDBResponse>> createCall() {

            }
        }.getAsLiveData();

    }*/



}
