package com.jmdev.greatmovies.test.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideosMovieRespose implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("results")
    @Expose
    private List<VideosMovie> videosMovie;

    protected VideosMovieRespose(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
    }

    public static final Creator<VideosMovieRespose> CREATOR = new Creator<VideosMovieRespose>() {
        @Override
        public VideosMovieRespose createFromParcel(Parcel in) {
            return new VideosMovieRespose(in);
        }

        @Override
        public VideosMovieRespose[] newArray(int size) {
            return new VideosMovieRespose[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVideosMovie(List<VideosMovie> videosMovie) {
        this.videosMovie = videosMovie;
    }

    public Integer getId() {
        return id;
    }

    public List<VideosMovie> getVideosMovie() {
        return videosMovie;
    }
}
