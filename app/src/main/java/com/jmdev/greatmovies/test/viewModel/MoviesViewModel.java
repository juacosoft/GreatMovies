package com.jmdev.greatmovies.test.viewModel;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jmdev.greatmovies.test.model.Movie;
import com.jmdev.greatmovies.test.model.MovieDBResponse;
import com.jmdev.greatmovies.networking.APIService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesViewModel extends ViewModel {//ViewModel
    private final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private final MutableLiveData<Boolean> moviesLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final MutableLiveData<Integer> totalPages = new MutableLiveData<Integer>();
    private final APIService apiService;
    public static final String QUERY_EXHAUSTED = "No more results.";


    private Call<MovieDBResponse> apiCall;

    @Inject
    public MoviesViewModel(APIService apiService) {
        this.apiService = apiService;
        fetchMovies();
    }



    private void fetchMovies() {
        loading.setValue(true);
        apiCall = apiService.getMovies();
        apiCall.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                moviesLoadError.setValue(false);
                movies.setValue(response.body().getMovies());
                totalPages.setValue(response.body().getTotalPages());
                loading.setValue(false);
                apiCall = null;

            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {
                moviesLoadError.setValue(true);
                loading.setValue(false);
                apiCall = null;
            }
        });
    }
    private void fetchRatedMovies(){
        loading.setValue(true);
        apiCall =apiService.getTopRated();
        apiCall.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                moviesLoadError.setValue(false);
                totalPages.setValue(response.body().getTotalPages());
                movies.setValue(response.body().getMovies());
                loading.setValue(false);
                apiCall=null;
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {
                moviesLoadError.setValue(true);
                loading.setValue(false);
                apiCall = null;
            }
        });
    }

    private void loadMore(int page,String option){
        apiCall=apiService.loadMore(option,page);
        apiCall.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                List<Movie>_templist=movies.getValue();
                _templist.addAll(response.body().getMovies());
                totalPages.setValue(response.body().getTotalPages());
                movies.postValue(_templist);

                apiCall=null;
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {
                apiCall = null;
            }
        });
    }
    public void searchMovie(String query,int page){
        loading.setValue(true);
        apiCall =apiService.searchMovie(query,page);
        apiCall.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                List<Movie>_tempList=movies.getValue()==null?new ArrayList():movies.getValue();
                _tempList.addAll(response.body().getMovies());
                totalPages.setValue(response.body().getTotalPages());
                movies.setValue(_tempList);
                moviesLoadError.setValue(false);
                loading.setValue(false);
                apiCall=null;
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {
                moviesLoadError.setValue(true);
                loading.setValue(false);
                apiCall = null;
            }
        });

    }
    public void loadMoreMovies(int page,String option){
        loadMore(page,option);
    }

    public void fetchRatedM(){
        fetchRatedMovies();
    }
    public void fetchPopular(){
        fetchMovies();
    }


    public LiveData<Integer>getTotalPages(){
        return totalPages;
    }
    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public LiveData<Boolean> getError() {
        return moviesLoadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    @Override
    protected void onCleared() {
        if (apiCall != null) {
            apiCall.cancel();
            apiCall = null;
        }
    }
}