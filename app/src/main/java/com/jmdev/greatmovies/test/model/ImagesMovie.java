package com.jmdev.greatmovies.test.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImagesMovie implements Parcelable {

    @SerializedName("aspect_ratio")
    @Expose
    private double aspectRatio;

    @SerializedName("file_path")
    @Expose
    private String filePath;

    @SerializedName("height")
    @Expose
    private int height;

    @SerializedName("iso_639_1")
    @Expose
    private String iso;

    @SerializedName("width")
    @Expose
    private int width;

    public static final Creator<ImagesMovie> CREATOR = new Creator<ImagesMovie>() {
        @SuppressWarnings({
                "unchecked"
        })
        public ImagesMovie createFromParcel(Parcel in) {
            return new ImagesMovie(in);
        }
        public ImagesMovie[] newArray(int size) {
            return new ImagesMovie[size];
        }
    };

    protected ImagesMovie(Parcel in) {
        aspectRatio = in.readDouble();
        filePath = in.readString();
        height = in.readInt();
        iso = in.readString();
        width = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(aspectRatio);
        dest.writeString(filePath);
        dest.writeInt(height);
        dest.writeString(iso);
        dest.writeInt(width);
    }

    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getAspectRatio() {
        return aspectRatio;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getHeight() {
        return height;
    }

    public String getIso() {
        return iso;
    }

    public int getWidth() {
        return width;
    }
}
