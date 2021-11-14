package com.jmdev.greatmovies.networking;



import static com.jmdev.greatmovies.test.util.Constants.API_KEY;

import com.jmdev.greatmovies.test.model.ImgMovieResponse;
import com.jmdev.greatmovies.test.model.MovieDBResponse;
import com.jmdev.greatmovies.test.model.MovieDetail;
import com.jmdev.greatmovies.test.model.VideosMovieRespose;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {



    @GET("movie/popular?api_key=" + API_KEY)
    Call<MovieDBResponse> getMovies();

    @GET("movie/{movie_id}?api_key=" + API_KEY + "&language=en-US")
    Call<MovieDetail> getMovieDetail(@Path("movie_id") String movieId);

    @GET("movie/top_rated?api_key=" + API_KEY)
    Call<MovieDBResponse> getTopRated();

    @GET("movie/{option}?api_key=" + API_KEY)
    Call<MovieDBResponse> loadMore(@Path("option")String option,@Query("page") int page);

    @GET("search/movie?api_key=" + API_KEY+"&include_adult=false&language=en-US")
    Call<MovieDBResponse> searchMovie(@Query("query")String query,@Query("page") int page);

    @GET("movie/{movie_id}/images?api_key=" + API_KEY+"&language=en-US")
    Call<ImgMovieResponse> getImages(@Path("movie_id") String movieId);

    @GET("movie/{movie_id}/videos?api_key="+API_KEY+"&language=en-US")
    Call <VideosMovieRespose>getVideos(@Path("movie_id") String movieId);

}