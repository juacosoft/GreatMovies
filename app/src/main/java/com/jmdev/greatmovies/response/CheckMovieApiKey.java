package com.jmdev.greatmovies.response;

import com.jmdev.greatmovies.test.model.MovieDBResponse;

public class CheckMovieApiKey {
    public static boolean isMovieKeyValid(MovieDBResponse response){
        return response.getError() == null;
    }

}
