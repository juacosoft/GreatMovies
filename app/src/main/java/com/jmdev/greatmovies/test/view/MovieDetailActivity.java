package com.jmdev.greatmovies.test.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmdev.greatmovies.R;
import com.jmdev.greatmovies.adapter.SliderAdapter;
import com.jmdev.greatmovies.base.App;
import com.jmdev.greatmovies.listener.ImageMovieListener;
import com.jmdev.greatmovies.test.model.Genre;
import com.jmdev.greatmovies.test.model.ImagesMovie;
import com.jmdev.greatmovies.test.model.Movie;
import com.jmdev.greatmovies.test.viewModel.SelectedMovieViewModel;
import com.jmdev.greatmovies.test.viewModel.ViewModelFactory;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements ImageMovieListener {
    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.imgMovieDetail)
    ImageView imgMovieDetail;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvGenre)
    TextView tvGenre;
    @BindView(R.id.tvdescription)
    TextView tvDescription;
    @BindView(R.id.imagescarrusel)
    RecyclerView sliderView;
    @BindView(R.id.trailervideo)
    YouTubePlayerView youTubePlayerView;
    @BindView(R.id.tvvotecount)
    TextView tvVoteCount;

    private SelectedMovieViewModel selectedMovieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        App.getAppComponent(this).inject(this);

        Intent intent = getIntent();
        if (intent.hasExtra("movie")) {
            Movie movie = getIntent().getParcelableExtra("movie");

            selectedMovieViewModel = ViewModelProviders.of(this, viewModelFactory).get(SelectedMovieViewModel.class);
            selectedMovieViewModel.setMovieId(movie.getId()+"");
            selectedMovieViewModel.restoreFromBundle(savedInstanceState);

            sliderView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
            sliderView.setAdapter((new SliderAdapter(selectedMovieViewModel, this, this)));
            sliderView.setLayoutManager(new GridLayoutManager(this, 3));

            showMovieDetail();
            loadImageCarrusel();
            loadVideosCarrulsel();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        selectedMovieViewModel.saveToBundle(outState);

        super.onSaveInstanceState(outState);
    }
    private void loadVideosCarrulsel(){
        selectedMovieViewModel.getVideos().observe(this,repos->{
            if(repos!=null){
                youTubePlayerView.setVisibility(View.VISIBLE);
                if(repos.size()>1){
                    addVideoToPlayer(repos.get(0).getVideKey());
                }
            }else{
                youTubePlayerView.setVisibility(View.GONE);
            }
        });
    }
    private void addVideoToPlayer(String videoKey){
        youTubePlayerView.setEnableAutomaticInitialization(false);

        youTubePlayerView.initialize(new AbstractYouTubePlayerListener() {
            @Override
            public void onCurrentSecond(YouTubePlayer youTubePlayer, float second) {
                super.onCurrentSecond(youTubePlayer, second);
                //if(second>1)youTubePlayer.pause();
            }

            @Override
            public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError error) {
                super.onError(youTubePlayer, error);
                youTubePlayerView.setVisibility(View.GONE);
            }

            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);

                youTubePlayer.loadVideo(videoKey,0);

            }
        }, true);
    }
    private void loadImageCarrusel(){
        selectedMovieViewModel.getPosters().observe(this,repos -> {
            if(repos==null||repos.isEmpty()){
                sliderView.setVisibility(View.GONE);
            }else{
                sliderView.setVisibility(View.VISIBLE);
            }

        });
    }

    private void showMovieDetail() {
        selectedMovieViewModel.getSelectedMovie().observe(this, movie -> {
            String imgPath = "https://image.tmdb.org/t/p/w500" + movie.getBackdropPath();
            Picasso.get()
                    .load(imgPath)
                    .placeholder(R.color.white)
                    .into(imgMovieDetail);

            tvTitle.setText(movie.getOriginalTitle());
            tvDescription.setVisibility(movie.getDescription()==null? View.GONE:View.VISIBLE);
            tvDescription.setText(movie.getDescription());
            tvVoteCount.setText(movie.getVoteCount()==null?"":movie.getVoteCount().toString());

            String movieGenre = "";
            for (Genre g:movie.getGenres()) {
                movieGenre = movieGenre + g.getName() + " - ";
            }
            tvGenre.setText(movieGenre);
        });

    }

    @Override
    public void onImageClicked(ImagesMovie imagesMovie) {

    }
}