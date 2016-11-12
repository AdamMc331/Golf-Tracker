package com.adammcneilly.golftracker.utility.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents a Hole played during a Game of golf.
 *
 * Created by adam.mcneilly on 11/12/16.
 */
public class Hole implements Parcelable{
    /**
     * The position of the hole on the course (1-18)
     */
    private int number;

    /**
     * The number of strokes expected to complete this hole.
     */
    private int par;

    /**
     * The number of strokes the user took to complete this hole. Default to 1, as 0 is impossible.
     */
    private int score = 1;

    public static Creator<Hole> CREATOR = new Creator<Hole>() {
        @Override
        public Hole createFromParcel(Parcel parcel) {
            return new Hole(parcel);
        }

        @Override
        public Hole[] newArray(int i) {
            return new Hole[i];
        }
    };

    public Hole(Parcel parcel) {
        this.number = parcel.readInt();
        this.par = parcel.readInt();
        this.score = parcel.readInt();
    }

    public Hole(int number, int par) {
        if(number < 1 || number > 18) {
            throw new IllegalArgumentException("Invalid hole number.");
        }

        this.number = number;
        this.par = par;
    }

    public void setScore(int score) {
        if(score < 1 || score > 10) {
            throw new IllegalArgumentException("Invalid score.");
        }

        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getNumber() {
        return number;
    }

    public int getValue() {
        return score - par;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(number);
        parcel.writeInt(par);
        parcel.writeInt(score);
    }
}
