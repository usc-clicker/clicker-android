package edu.usc.clicker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ian on 10/15/15.
 */
public class LoginBody {
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    public LoginBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
