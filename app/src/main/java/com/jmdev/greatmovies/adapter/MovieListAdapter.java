package com.jmdev.greatmovies.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.jmdev.greatmovies.R;
import com.jmdev.greatmovies.listener.MovieSelectedListener;
import com.jmdev.greatmovies.test.model.Movie;
import com.jmdev.greatmovies.test.util.Constants;
import com.jmdev.greatmovies.test.viewModel.MoviesViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private final List<Movie> movies = new ArrayList<>();
    private final MovieSelectedListener movieSelectedListener;

    public MovieListAdapter(MoviesViewModel viewModel, LifecycleOwner lifecycleOwner, MovieSelectedListener movieSelectedListener) {
        this.movieSelectedListener = movieSelectedListener;
        viewModel.getMovies().observe(lifecycleOwner, repos -> {
            movies.clear();
            if (repos != null) {
                movies.addAll(repos);
            }
            notifyDataSetChanged();
        });
        setHasStableIds(true);
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_movie_list_item, parent, false);
        return new MovieViewHolder(view, movieSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {

        movieViewHolder.bind(movies.get(i));
    }

    @Override
    public long getItemId(int position) {
        return movies.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static final class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgMovie)
        ImageView imgMovie;
        @BindView(R.id.tvMovieTitle)
        TextView tvMovieTitle;
        @BindView(R.id.tvvotecount)
        TextView tvVoteCount;


        private Movie movie;

        MovieViewHolder(@NonNull View itemView, final MovieSelectedListener movieSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (movie != null) {
                    movieSelectedListener.onMovieSelected(movie);
                }
            });
        }

        void bind(Movie movie) {
            this.movie = movie;
            tvMovieTitle.setText(movie.getTitle());
            tvVoteCount.setText(movie.getVoteCount().toString());
            String imgPath = Constants.IMAGE_PATH + movie.getBackdropPath();
            Picasso.get()
                    .load(imgPath)
                    .placeholder(R.color.white)
                    .into(imgMovie);
        }
    }
}