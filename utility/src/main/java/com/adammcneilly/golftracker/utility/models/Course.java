package com.adammcneilly.golftracker.utility.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a course someone can play a game at.
 *
 * Created by adam.mcneilly on 11/12/16.
 */

public class Course implements Parcelable {
    private String name;
    /**
     * List of holes for the course.
     */
    private List<Hole> holes = new ArrayList<>();

    public static Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel parcel) {
            return new Course(parcel);
        }

        @Override
        public Course[] newArray(int i) {
            return new Course[i];
        }
    };

    public Course(Parcel parcel) {
        parcel.readList(holes, Hole.class.getClassLoader());
    }

    public Course(String name, List<Hole> courseHoles) {
        if(courseHoles.size() > 18) {
            throw new IllegalArgumentException("Invalid number of holes.");
        }

        this.name = name;
        this.holes = courseHoles;
    }

    public List<Hole> getHoles() {
        return holes;
    }

    public String getName() {
        return name;
    }

    public Hole getHole(int number) {
        if(number < 0 || number > holes.size()) {
            throw new IllegalArgumentException("Invalid hole number.");
        }

        return holes.get(number);
    }

    public int getPar() {
        int par = 0;

        for(Hole hole : holes) {
            par += hole.getPar();
        }

        return par;
    }

    public static Course getTestCourse(String name) {
        List<Hole> holes = Hole.getTestHoles(3, 3);
        return new Course(name, holes);
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
