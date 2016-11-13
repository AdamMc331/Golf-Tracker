package com.adammcneilly.golftracker.utility.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Game of golf.
 *
 * Created by adam.mcneilly on 11/12/16.
 */
public class Game implements Parcelable {
    private static final String LOG_TAG = Game.class.getSimpleName();
    private Course course;

    /**
     * List of holes that the user played. Used unbounded list because user might play less than 18,
     * but we will not allow more.
     */
    private List<Hole> holes = new ArrayList<>();

    public static Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel parcel) {
            return new Game(parcel);
        }

        @Override
        public Game[] newArray(int i) {
            return new Game[i];
        }
    };

    public Game(Parcel parcel) {
        this.course = parcel.readParcelable(Course.class.getClassLoader());
        parcel.readList(holes, Hole.class.getClassLoader());
    }

    public Game(Course course) {
        this.course = course;
        this.holes = new ArrayList<>(course.getHoles().size());
    }

    public Game(List<Hole> gameHoles) {
        if(gameHoles.size() > 18) {
            throw new IllegalArgumentException("Invalid number of holes.");
        }

        this.holes = gameHoles;
    }

    public List<Hole> getHoles() {
        return holes;
    }

    public Hole getHole(int number) {
        if(number < 0 || number > holes.size()) {
            throw new IllegalArgumentException("Invalid hole number.");
        }

        return holes.get(number);
    }

    public int getScore() {
        int score = 0;

        for(Hole hole : holes) {
            score += hole.getScore();
        }

        return score;
    }

    public static Game getPar3Game() {
        return new Game(Hole.getTestHoles(3, 3));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(course, 0);
        parcel.writeList(holes);
    }
}
