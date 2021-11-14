package com.jmdev.greatmovies.test.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideosMovie implements Parcelable {

    @SerializedName("iso_639_1")
    @Expose
    private String iso_639_1;

    @SerializedName("iso_3166_1")
    @Expose
    private String iso_3166_1;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("key")
    @Expose
    private String videKey;

    @SerializedName("site")
    @Expose
    private String site;

    @SerializedName("size")
    @Expose
    private int size;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("official")
    @Expose
    private boolean offcial;

    @SerializedName("published_at")
    @Expose
    private String publishedAt;

    @SerializedName("id")
    @Expose
    private String id;


    protected VideosMovie(Parcel in) {
        iso_639_1 = in.readString();
        iso_3166_1 = in.readString();
        name = in.readString();
        videKey = in.readString();
        site = in.readString();
        size = in.readInt();
        type = in.readString();
        offcial = in.readByte() != 0;
        publishedAt = in.readString();
        id = in.readString();
    }

    public static final Creator<VideosMovie> CREATOR = new Creator<VideosMovie>() {
        @Override
        public VideosMovie createFromParcel(Parcel in) {
            return new VideosMovie(in);
        }

        @Override
        public VideosMovie[] newArray(int size) {
            return new VideosMovie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iso_639_1);
        dest.writeString(iso_3166_1);
        dest.writeString(name);
        dest.writeString(videKey);
        dest.writeString(site);
        dest.writeInt(size);
        dest.writeString(type);
        dest.writeByte((byte) (offcial ? 1 : 0));
        dest.writeString(publishedAt);
        dest.writeString(id);
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVideKey(String videKey) {
        this.videKey = videKey;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOffcial(boolean offcial) {
        this.offcial = offcial;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public String getName() {
        return name;
    }

    public String getVideKey() {
        return videKey;
    }

    public String getSite() {
        return site;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public boolean isOffcial() {
        return offcial;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getId() {
        return id;
    }
}
