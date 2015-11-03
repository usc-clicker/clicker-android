package edu.usc.clicker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterBody {
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("student_id")
    @Expose
    private long studentID;

    public RegisterBody(String email, String password, long studentID) {
        this.email = email;
        this.password = password;
        this.studentID = studentID;
    }
}
