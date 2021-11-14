package com.jmdev.greatmovies.test.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImgMovieResponse implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("backdrops")
    @Expose
    private List<ImagesMovie> backDrops;

    @SerializedName("posters")
    @Expose
    private List<ImagesMovie> posters;

    protected ImgMovieResponse(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        backDrops = in.readParcelable(ImagesMovie.class.getClassLoader());
        posters = in.readParcelable(ImagesMovie.class.getClassLoader());
    }

    public static final Creator<ImgMovieResponse> CREATOR = new Creator<ImgMovieResponse>() {
        @Override
        public ImgMovieResponse createFromParcel(Parcel in) {
            return new ImgMovieResponse(in);
        }

        @Override
        public ImgMovieResponse[] newArray(int size) {
            return new ImgMovieResponse[size];
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
        dest.readList(this.backDrops, (ImagesMovie.class.getClassLoader()));
        dest.readList(this.posters, (ImagesMovie.class.getClassLoader()));
        //dest.writeParcelable(posters, flags);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBackDrops(List<ImagesMovie> backDrops) {
        this.backDrops = backDrops;
    }

    public void setPosters(List<ImagesMovie> posters) {
        this.posters = posters;
    }

    public Integer getId() {
        return id;
    }

    public List<ImagesMovie> getBackDrops() {
        return backDrops;
    }

    public List<ImagesMovie> getPosters() {
        return posters;
    }
}
