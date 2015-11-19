package edu.usc.clicker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class User {

    @SerializedName("attempts")
    @Expose
    private List<Object> attempts = new ArrayList<Object>();
    @SerializedName("jsonWebTokens")
    @Expose
    private List<Object> jsonWebTokens = new ArrayList<Object>();
    @SerializedName("auth")
    @Expose
    private Auth auth;
    @SerializedName("enrolledIn")
    @Expose
    private List<String> enrolledIn = new ArrayList<String>();
    @SerializedName("answerSets")
    @Expose
    private List<Long> answerSets = new ArrayList<Long>();
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    public List<Object> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Object> attempts) {
        this.attempts = attempts;
    }

    public List<Object> getJsonWebTokens() {
        return jsonWebTokens;
    }

    public void setJsonWebTokens(List<Object> jsonWebTokens) {
        this.jsonWebTokens = jsonWebTokens;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public List<String> getEnrolledIn() {
        return enrolledIn;
    }

    public void setEnrolledIn(List<String> enrolledIn) {
        this.enrolledIn = enrolledIn;
    }

    public List<Long> getAnswerSets() {
        return answerSets;
    }

    public void setAnswerSets(List<Long> answerSets) {
        this.answerSets = answerSets;
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

}