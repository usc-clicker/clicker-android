package edu.usc.clicker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("choices")
    @Expose
    private List<String> choices = new ArrayList<String>();
    @SerializedName("push_hash")
    @Expose
    private String pushHash;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("expiration")
    @Expose
    private long expiration;
    @SerializedName("time_limit")
    @Expose
    private long timeLimit;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("show_answers")
    @Expose
    private boolean showAnswers;
    @SerializedName("quiz_id")
    @Expose
    public int quizID;

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getChoices() {
        return choices;
    }

    public String getPushHash() {
        return pushHash;
    }

    public String getQuestion() {
        return question;
    }

    public long getExpiration() {
        return expiration;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public String getType() {
        return type;
    }

    public boolean getShowAnsers() {
        return showAnswers;
    }

    public int getQuizID() {
        return quizID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.answer);
        dest.writeStringList(this.choices);
        dest.writeString(this.pushHash);
        dest.writeString(this.question);
        dest.writeLong(this.expiration);
        dest.writeLong(this.timeLimit);
        dest.writeString(this.type);
        dest.writeByte((byte) (this.showAnswers ? 1 : 0));
        dest.writeInt(this.quizID);
    }

    public MultipleChoiceQuestion() {
    }

    protected MultipleChoiceQuestion(Parcel in) {
        this.id = in.readInt();
        this.answer = in.readString();
        this.choices = in.createStringArrayList();
        this.pushHash = in.readString();
        this.question = in.readString();
        this.expiration = in.readLong();
        this.timeLimit = in.readLong();
        this.type = in.readString();
        this.showAnswers = in.readByte() != 0;
        this.quizID = in.readInt();
    }

    public static final Parcelable.Creator<MultipleChoiceQuestion> CREATOR = new Parcelable.Creator<MultipleChoiceQuestion>() {
        public MultipleChoiceQuestion createFromParcel(Parcel source) {
            return new MultipleChoiceQuestion(source);
        }

        public MultipleChoiceQuestion[] newArray(int size) {
            return new MultipleChoiceQuestion[size];
        }
    };
}