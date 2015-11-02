package edu.usc.clicker.model;

import android.location.Location;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class AnswerResponse {

    @SerializedName("quiz_id")
    @Expose
    private long quizId;
    @SerializedName("question_id")
    @Expose
    private long questionId;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("location")
    @Expose
    private LocationResponse locationResponse = new LocationResponse();
    @SerializedName("user")
    @Expose
    private String user;

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocationResponse getLocationResponse() {
        return locationResponse;
    }

    public void setLocationResponse(Location location) {
        LocationResponse locationResponse = new LocationResponse();
        locationResponse.setLat(location.getLatitude());
        locationResponse.setLng(location.getLongitude());
        this.locationResponse = locationResponse;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}