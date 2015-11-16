package edu.usc.clicker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationBody implements Parcelable {

    @SerializedName("lat")
    @Expose
    private double lat = 0;
    @SerializedName("lng")
    @Expose
    private double lng = 0;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lng);
    }

    public LocationBody() {
    }

    protected LocationBody(Parcel in) {
        this.lat = in.readDouble();
        this.lng = in.readDouble();
    }

    public static final Parcelable.Creator<LocationBody> CREATOR = new Parcelable.Creator<LocationBody>() {
        public LocationBody createFromParcel(Parcel source) {
            return new LocationBody(source);
        }

        public LocationBody[] newArray(int size) {
            return new LocationBody[size];
        }
    };
}