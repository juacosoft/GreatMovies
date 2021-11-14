package com.jmdev.greatmovies.test.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jmdev.greatmovies.R;
import com.jmdev.greatmovies.adapter.SearchListAdapter;
import com.jmdev.greatmovies.base.App;
import com.jmdev.greatmovies.listener.MovieSelectedListener;
import com.jmdev.greatmovies.test.model.Movie;
import com.jmdev.greatmovies.test.viewModel.SearchViewModel;
import com.jmdev.greatmovies.test.viewModel.ViewModelFactory;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultsActivity extends AppCompatActivity implements MovieSelectedListener {
    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.rvMoviesList)
    RecyclerView rvMoviesList;
    @BindView(R.id.tvErrorMsg)
    TextView tvErrorMsg;
    @BindView(R.id.loadingMovies)
    ProgressBar loadingMovies;
    @BindView(R.id.tvTitleMovie)
    TextView tvTitleMovie;
    @BindView(R.id.spLayout)
    SwipyRefreshLayout spRefresh;

    private int pageInt=1;
    private SearchViewModel searchViewModel;
    String query="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);

        App.getAppComponent(this).inject(this);
        Intent intent=getIntent();
        spRefresh.setEnabled(false);
        if(intent.hasExtra("query")){

            query = getIntent().getStringExtra("query");
            Log.d("getQuery",query);
            tvTitleMovie.setText("Results of: "+query);
            searchViewModel= ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel.class);
            searchViewModel.searchMovie(query,pageInt);
            rvMoviesList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            rvMoviesList.setAdapter((new SearchListAdapter(searchViewModel, this, this)));
            rvMoviesList.setLayoutManager(new GridLayoutManager(this, 3));
            observeMoviesViewModel();
            spRefresh.setEnabled(true);

        }
        spRefresh.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if(!query.isEmpty()){
                    pageInt=pageInt+1;
                    searchViewModel.searchMovie(query,pageInt);

                }


            }
        });


    }
    private void observeMoviesViewModel(){
        searchViewModel.getTotalPages().observe(this,total->{
            if(total>pageInt){
                spRefresh.setEnabled(true);
            }else{
                spRefresh.setEnabled(false);
                pageInt=total;
            }
        });
        searchViewModel.getMovies().observe(this,repos->{
            if(repos != null){
                rvMoviesList.setVisibility(View.VISIBLE);
                spRefresh.setRefreshing(false);

            }
        });
        searchViewModel.getError().observe(this,isError->{
            if(isError){
                tvErrorMsg.setVisibility(View.VISIBLE);
                rvMoviesList.setVisibility(View.GONE);
                tvErrorMsg.setText("Error no media");
                spRefresh.setRefreshing(false);
            }else{
                tvErrorMsg.setVisibility(View.GONE);
                tvErrorMsg.setText(null);
            }
        });
        searchViewModel.getLoading().observe(this,isLoading->{
            loadingMovies.setVisibility(isLoading?View.VISIBLE:View.GONE);
            if(isLoading){

                tvErrorMsg.setVisibility(View.GONE);
                rvMoviesList.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onMovieSelected(Movie movie) {
        Intent intent = new Intent(SearchResultsActivity.this, MovieDetailActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}