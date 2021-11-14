package com.jmdev.greatmovies.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.jmdev.greatmovies.R;
import com.jmdev.greatmovies.test.model.VideosMovie;
import com.jmdev.greatmovies.test.viewModel.SelectedMovieViewModel;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideosAdapter extends  RecyclerView.Adapter<VideosAdapter.VideosAdapterViewHolder>{
    private final List<VideosMovie> videos=new ArrayList<>();
    private final LifecycleOwner lifecycle;

    public VideosAdapter(SelectedMovieViewModel viewModel, LifecycleOwner lifecycleOwner){
        this.lifecycle=lifecycleOwner;
        viewModel.getVideos().observe(lifecycleOwner,repos->{
            videos.clear();
            if(repos!=null){
                videos.addAll(repos);
            }
            notifyDataSetChanged();
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public VideosAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
        return new VideosAdapterViewHolder(view,lifecycle);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdapterViewHolder holder, int position) {
        holder.bind(videos.get(position));
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    static class VideosAdapterViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        @BindView(R.id.videoYoutube)
        YouTubePlayerView videoPlayer;


        public VideosAdapterViewHolder(View itemView,LifecycleOwner lifecycleOwner) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
            lifecycleOwner.getLifecycle().addObserver(videoPlayer);
        }
        void bind(VideosMovie video){

           /* videoPlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    Log.d("LoadVideo",video.getVideKey());
                    youTubePlayer.loadVideo(video.getVideKey(),0);
                }

            });*/
        }
    }
}
