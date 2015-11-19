package edu.usc.clicker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  RegisterBody {
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("usc_id")
    @Expose
    private long uscID;

    public RegisterBody(String email, String password, long uscID) {
        this.email = email;
        this.password = password;
        this.uscID = uscID;
    }
}
