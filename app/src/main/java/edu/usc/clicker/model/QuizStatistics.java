package edu.usc.clicker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizStatistics {

    @SerializedName("quiz_name")
    @Expose
    private String quizName;
    @SerializedName("score")
    @Expose
    private double score;

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}