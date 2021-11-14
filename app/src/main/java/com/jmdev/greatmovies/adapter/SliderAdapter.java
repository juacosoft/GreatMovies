package com.jmdev.greatmovies.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.jmdev.greatmovies.R;
import com.jmdev.greatmovies.listener.ImageMovieListener;
import com.jmdev.greatmovies.test.model.ImagesMovie;
import com.jmdev.greatmovies.test.util.Constants;
import com.jmdev.greatmovies.test.viewModel.SelectedMovieViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderAdapterViewHolder>{

    private final List<ImagesMovie> mSliderItems=new ArrayList<>();
    private final ImageMovieListener imageMovieListener;

    // Constructor
    public SliderAdapter(SelectedMovieViewModel viewModel, LifecycleOwner lifecycleOwner, ImageMovieListener imageMovieListener) {
        this.imageMovieListener=imageMovieListener;
        viewModel.getPosters().observe(lifecycleOwner,repos->{
            mSliderItems.clear();
            if (repos != null) {
                mSliderItems.addAll(repos);
            }
            notifyDataSetChanged();
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_images, parent, false);
        return new SliderAdapterViewHolder(view, imageMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapterViewHolder sliderViewHolder, int i) {
        sliderViewHolder.bind(mSliderItems.get(i));
    }


    @Override
    public int getItemCount() {
        return mSliderItems.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    static class SliderAdapterViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        @BindView(R.id.imgposter)
        ImageView imagePoster;

        public SliderAdapterViewHolder(View itemView,ImageMovieListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
        void bind(ImagesMovie imagesMovie){
            Picasso.get()
                    .load(Constants.IMAGE_PATH+imagesMovie.getFilePath())
                    .centerCrop()
                    .into(imagePoster);
        }
    }
}
