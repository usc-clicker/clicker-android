package edu.usc.clicker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnrollBody {
    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("section_id")
    @Expose
    private long sectionID;

    public EnrollBody(String user, long sectionID) {
        this.user = user;
        this.sectionID = sectionID;
    }
}
