package com.jmdev.greatmovies.adapter;

import android.util.Log;
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
import com.jmdev.greatmovies.test.viewModel.SearchViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.MovieViewHolder> {

    private final List<Movie> movies = new ArrayList<>();
    private final MovieSelectedListener movieSelectedListener;
    public SearchListAdapter(SearchViewModel viewModel, LifecycleOwner lifecycleOwner, MovieSelectedListener movieSelectedListener) {
        Log.d("ConstructAdapter","init");
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
            String imgPath = "https://image.tmdb.org/t/p/w500" + movie.getBackdropPath();
            Picasso.get()
                    .load(imgPath)
                    .placeholder(R.color.white)
                    .into(imgMovie);
        }
    }
}
