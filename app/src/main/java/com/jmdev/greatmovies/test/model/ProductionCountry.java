package com.jmdev.greatmovies.test.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductionCountry implements Parcelable {
    @SerializedName("iso_3166_1")
    @Expose
    private String iso31661;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Creator<ProductionCountry> CREATOR = new Creator<ProductionCountry>() {
        @SuppressWarnings({"unchecked"})
        public ProductionCountry createFromParcel(Parcel in) {
            return new ProductionCountry(in);
        }

        public ProductionCountry[] newArray(int size) {
            return (new ProductionCountry[size]);
        }
    };

    protected ProductionCountry(Parcel in) {
        this.iso31661 = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProductionCountry() {
    }

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iso31661);
        dest.writeValue(name);
    }

    public int describeContents() {
        return 0;
    }
}