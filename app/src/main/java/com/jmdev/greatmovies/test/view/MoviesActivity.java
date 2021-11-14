package com.jmdev.greatmovies.test.view;

import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


import android.widget.TextView;

import com.jmdev.greatmovies.R;
import com.jmdev.greatmovies.adapter.MovieListAdapter;
import com.jmdev.greatmovies.base.App;
import com.jmdev.greatmovies.listener.MovieSelectedListener;
import com.jmdev.greatmovies.test.model.Movie;
import com.jmdev.greatmovies.test.viewModel.MoviesViewModel;
import com.jmdev.greatmovies.test.viewModel.ViewModelFactory;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesActivity extends AppCompatActivity implements MovieSelectedListener, View.OnClickListener {
    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.rvMoviesList)
    RecyclerView rvMoviesList;
    @BindView(R.id.tvErrorMsg)
    TextView tvErrorMsg;
    @BindView(R.id.loadingMovies)
    ProgressBar loadingMovies;
    @BindView(R.id.tvTopRated)
    TextView tvTopRated;
    @BindView(R.id.tvPopuar)
    TextView tvPopular;
    @BindView(R.id.spLayout)
    SwipyRefreshLayout spRefresh;



    private int pageInt=1;

    private MoviesViewModel moviesViewModel;
    private String option="popular";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        App.getAppComponent(this).inject(this);

        moviesViewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel.class);

        rvMoviesList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvMoviesList.setAdapter((new MovieListAdapter(moviesViewModel, this, this)));
        rvMoviesList.setLayoutManager(new GridLayoutManager(this, 3));
        observeMoviesViewModel();


        tvTopRated.setOnClickListener(this);
        tvPopular.setOnClickListener(this);
        spRefresh.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                pageInt=pageInt+1;
                moviesViewModel.loadMoreMovies(pageInt,option);

            }
        });




    }

    @Override
    public void onMovieSelected(Movie movie) {
        Intent intent = new Intent(MoviesActivity.this, MovieDetailActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_out_b, R.anim.slide_in_b);
    }

    private void observeMoviesViewModel() {
        moviesViewModel.getTotalPages().observe(this,total->{
            if(total>pageInt){
                spRefresh.setEnabled(true);
            }else{
                spRefresh.setEnabled(false);
                pageInt=total;
            }
        });
        moviesViewModel.getMovies().observe(this, repos -> {

            if (repos != null) {
                rvMoviesList.setVisibility(View.VISIBLE);
                spRefresh.setRefreshing(false);
            }
        });
        moviesViewModel.getError().observe(this, isError -> {
            if (isError) {
                tvErrorMsg.setVisibility(View.VISIBLE);
                rvMoviesList.setVisibility(View.GONE);
                tvErrorMsg.setText("Error no media");
                spRefresh.setRefreshing(false);
            } else {
                tvErrorMsg.setVisibility(View.GONE);
                tvErrorMsg.setText(null);
                spRefresh.setRefreshing(false);
            }
        });
        moviesViewModel.getLoading().observe(this, isLoading -> {
            loadingMovies.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if (isLoading) {
                tvErrorMsg.setVisibility(View.GONE);
                rvMoviesList.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.tvTopRated){
            moviesViewModel.fetchRatedM();
            option="top_rated";

        }else if(id==R.id.tvPopuar){
            moviesViewModel.fetchPopular();
            option="popular";
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu,menu);
        final MenuItem searchItem=menu.findItem(R.id.action_search);
        final SearchView searchView=(SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search By name ...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("ActionSearch",query);
                Intent intent=new Intent(MoviesActivity.this,SearchResultsActivity.class);
                intent.putExtra("query",query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}