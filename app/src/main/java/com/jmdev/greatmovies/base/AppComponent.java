package com.jmdev.greatmovies.base;



import com.jmdev.greatmovies.module.NetworkModule;
import com.jmdev.greatmovies.module.ViewModelModule;
import com.jmdev.greatmovies.test.view.MovieDetailActivity;
import com.jmdev.greatmovies.test.view.MoviesActivity;
import com.jmdev.greatmovies.test.view.SearchResultsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ViewModelModule.class})
public interface AppComponent {
    void inject(MoviesActivity moviesActivity);
    void inject(MovieDetailActivity movieDetailActivity);
    void inject(SearchResultsActivity searchResultsActivity);
}