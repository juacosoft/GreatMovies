package com.jmdev.greatmovies.test.viewModel;


import android.os.Bundle;
import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.jmdev.greatmovies.test.model.ImagesMovie;
import com.jmdev.greatmovies.test.model.ImgMovieResponse;
import com.jmdev.greatmovies.test.model.MovieDetail;
import com.jmdev.greatmovies.test.model.VideosMovie;
import com.jmdev.greatmovies.test.model.VideosMovieRespose;
import com.jmdev.greatmovies.networking.APIService;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedMovieViewModel extends ViewModel {
    private final MutableLiveData<MovieDetail> movieSelected = new MutableLiveData<>();
    private MutableLiveData<String> movieId = new MutableLiveData<>();
    private MutableLiveData<List<ImagesMovie>>imagesMove=new MutableLiveData<>();
    private MutableLiveData<List<VideosMovie>>videosMovie=new MutableLiveData<>();

    private final APIService apiService;
    private Call<MovieDetail> apiCall;
    private Call<ImgMovieResponse>apiCallImages;
    private Call <VideosMovieRespose>apiCallVideo;

    @Inject
    public SelectedMovieViewModel(APIService apiService) {
        this.apiService = apiService;
    }

    public LiveData<MovieDetail> getSelectedMovie() {
        return movieSelected;
    }

    public void setSelectMovie(MovieDetail movieDetail) {
        movieSelected.setValue(movieDetail);
    }
    public LiveData<List<ImagesMovie>>getPosters(){
        return imagesMove;
    }
    public LiveData<List<VideosMovie>>getVideos(){return videosMovie;}

    public void setMovieId(String movie_Id) {
        movieId.setValue(movie_Id+"");
    }

    public void saveToBundle(Bundle outState) {
        if (movieSelected.getValue() != null) {
            outState.putString("movie_details", movieSelected.getValue().getId().toString());
        }
        if(imagesMove.getValue()!=null){
            outState.putString("images_movie",movieSelected.getValue().getId().toString());
        }
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if (movieSelected.getValue() == null) {
            if (savedInstanceState != null && savedInstanceState.containsKey("repo_details")) {
                loadMovie(Objects.requireNonNull(savedInstanceState.getString("repo_details")));
            } else {
                loadMovie(movieId.getValue());
            }
        }
        if (imagesMove.getValue() == null) {
            if (savedInstanceState != null && savedInstanceState.containsKey("repo_details")) {
                loadImagesMovie(Objects.requireNonNull(savedInstanceState.getString("repo_details")));
            } else {
                loadImagesMovie(movieId.getValue());
            }
        }
        if(videosMovie.getValue()==null){
            if (savedInstanceState != null && savedInstanceState.containsKey("repo_details")) {
                loadImagesMovie(Objects.requireNonNull(savedInstanceState.getString("repo_details")));
            } else {
                loadVideos(movieId.getValue());
            }
        }
    }
    private void loadVideos(String movieId){
        apiCallVideo=apiService.getVideos(movieId);
        apiCallVideo.enqueue(new Callback<VideosMovieRespose>() {
            @Override
            public void onResponse(Call<VideosMovieRespose> call, Response<VideosMovieRespose> response) {
                Log.d("Videos",new Gson().toJson(response.body()));
                videosMovie.setValue(response.body().getVideosMovie());
                apiCallVideo=null;

            }

            @Override
            public void onFailure(Call<VideosMovieRespose> call, Throwable t) {
                Log.d("VideosError",t.getMessage());

            }
        });
    }
    private void loadImagesMovie(String movieId){

        apiCallImages =apiService.getImages(movieId);
        apiCallImages.enqueue(new Callback<ImgMovieResponse>() {
            @Override
            public void onResponse(Call<ImgMovieResponse> call, Response<ImgMovieResponse> response) {
                //if(response.isSuccessful()){
                    imagesMove.setValue(response.body().getPosters());
                    Gson gson=new Gson();
                    Log.d("Posters",gson.toJson(response.body().getPosters()));
                //}
                Log.d("ImageResponse",response.message());
                apiCallImages=null;
            }

            @Override
            public void onFailure(Call<ImgMovieResponse> call, Throwable t) {
                apiCallImages=null;
                Log.d("Posters",t.getMessage());
                Log.e(getClass().getSimpleName(), "Error getting movie posters", t);
            }
        });
    }
    private void loadMovie(String movieId) {
        apiCall = apiService.getMovieDetail(movieId);
        apiCall.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                movieSelected.setValue(response.body());
                apiCall = null;
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.e(getClass().getSimpleName(), "Error getting movie details", t);
                apiCall = null;
            }
        });
    }

    @Override
    protected void onCleared() {
        if(apiCallImages!=null){
            apiCallImages.cancel();
            apiCallImages=null;
        }
        if(apiCallVideo!=null){
            apiCallVideo.cancel();
            apiCallVideo=null;
        }
        if (apiCall != null) {
            apiCall.cancel();
            apiCall = null;
        }
    }
}