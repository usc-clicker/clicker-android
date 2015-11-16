package edu.usc.clicker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FreeResponseQuestion implements Parcelable {

    @SerializedName("answer")
    @Expose
    private String answer;
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

    public String getAnswer() {
        return answer;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.answer);
        dest.writeString(this.pushHash);
        dest.writeString(this.question);
        dest.writeLong(this.expiration);
        dest.writeLong(this.timeLimit);
        dest.writeString(this.type);
    }

    public FreeResponseQuestion() {
    }

    protected FreeResponseQuestion(Parcel in) {
        this.answer = in.readString();
        this.pushHash = in.readString();
        this.question = in.readString();
        this.expiration = in.readLong();
        this.timeLimit = in.readLong();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<FreeResponseQuestion> CREATOR = new Parcelable.Creator<FreeResponseQuestion>() {
        public FreeResponseQuestion createFromParcel(Parcel source) {
            return new FreeResponseQuestion(source);
        }

        public FreeResponseQuestion[] newArray(int size) {
            return new FreeResponseQuestion[size];
        }
    };
}