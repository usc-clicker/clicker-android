package edu.usc.clicker.model;

import android.location.Location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    private LocationBody locationBody = new LocationBody();
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

    public LocationBody getLocationBody() {
        return locationBody;
    }

    public void setLocationBody(Location location) {
        LocationBody locationBody = new LocationBody();
        locationBody.setLat(location.getLatitude());
        locationBody.setLng(location.getLongitude());
        this.locationBody = locationBody;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}