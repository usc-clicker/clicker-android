package edu.usc.clicker.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("start_time")
    @Expose
    private long startTime;
    @SerializedName("time_limit")
    @Expose
    private long timeLimit;
    @SerializedName("type")
    @Expose
    private String type;

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

    public long getStartTime() {
        return startTime;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public String getType() {
        return type;
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
        dest.writeLong(this.startTime);
        dest.writeLong(this.timeLimit);
        dest.writeString(this.type);
    }

    public MultipleChoiceQuestion() {
    }

    protected MultipleChoiceQuestion(Parcel in) {
        this.id = in.readInt();
        this.answer = in.readString();
        this.choices = in.createStringArrayList();
        this.pushHash = in.readString();
        this.question = in.readString();
        this.startTime = in.readLong();
        this.timeLimit = in.readLong();
        this.type = in.readString();
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