package com.jmdev.greatmovies.test.viewModel;

import android.util.Log;

import androidx.annotation.NonNull;
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

public class SearchViewModel extends ViewModel {
    private final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private final MutableLiveData<Boolean> moviesLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final MutableLiveData<Integer> totalPages = new MutableLiveData<Integer>();



    private final APIService apiService;

    private Call<MovieDBResponse> apiCall;

    @Inject
    public SearchViewModel(APIService apiService) {
        this.apiService = apiService;
    }

    public void searchMovie(String query,int page){
        loading.setValue(true);
        apiCall =apiService.searchMovie(query,page);

        apiCall.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieDBResponse> call, @NonNull Response<MovieDBResponse> response) {
                Log.d("messageRequest",response.message());
                if (response.isSuccessful()) {
                    List<Movie> _tempList = movies.getValue() == null ? new ArrayList() : movies.getValue();
                    _tempList.addAll(response.body().getMovies());
                    totalPages.setValue(response.body().getTotalPages());
                    movies.setValue(_tempList);

                    moviesLoadError.setValue(false);
                    loading.setValue(false);

                }
                apiCall = null;
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {
                moviesLoadError.setValue(true);
                loading.setValue(false);
                apiCall = null;
                totalPages.setValue(0);

            }
        });

    }
    public LiveData<Integer> getTotalPages(){
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
