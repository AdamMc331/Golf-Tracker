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
        parcel.readList(holes, null);
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
        List<Hole> holes = new ArrayList<>();

        for(int i = 1; i <= 3; i++) {
            Log.v(LOG_TAG, "Hole number: " + i);
            holes.add(new Hole(i, 3));
        }

        return new Game(holes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(holes);
    }
}
