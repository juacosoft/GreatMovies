package com.jmdev.greatmovies.module;


import androidx.lifecycle.ViewModel;

import com.jmdev.greatmovies.test.viewModel.MoviesViewModel;
import com.jmdev.greatmovies.test.viewModel.SearchViewModel;
import com.jmdev.greatmovies.test.viewModel.SelectedMovieViewModel;
import com.jmdev.greatmovies.test.viewModel.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel.class)
    abstract ViewModel bindMoviesViewModel(MoviesViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SelectedMovieViewModel.class)
    abstract ViewModel bindMovieDetailViewModel(SelectedMovieViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    abstract ViewModel binSearchViewModel(SearchViewModel viewModel);
}