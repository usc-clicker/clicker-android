package edu.usc.clicker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Section implements Parcelable {

    @SerializedName("course_id")
    @Expose
    private String courseID;
    @SerializedName("section_id")
    @Expose
    private String sectionID;
    @SerializedName("instructor")
    @Expose
    private String instructor;
    @SerializedName("location")
    @Expose
    private LocationBody location;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(LocationBody location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.courseID);
        dest.writeString(this.sectionID);
        dest.writeString(this.instructor);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.startTime);
        dest.writeString(this.endTime);
        dest.writeLong(this.id);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public Section() {
    }

    protected Section(Parcel in) {
        this.courseID = in.readString();
        this.sectionID = in.readString();
        this.instructor = in.readString();
        this.location = in.readParcelable(Object.class.getClassLoader());
        this.startTime = in.readString();
        this.endTime = in.readString();
        this.id = in.readLong();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<Section> CREATOR = new Parcelable.Creator<Section>() {
        public Section createFromParcel(Parcel source) {
            return new Section(source);
        }

        public Section[] newArray(int size) {
            return new Section[size];
        }
    };
}