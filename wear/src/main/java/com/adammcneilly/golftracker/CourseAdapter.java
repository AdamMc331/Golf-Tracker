package com.adammcneilly.golftracker;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adammcneilly.golftracker.utility.models.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for the courses to choose from.
 *
 * Created by adam.mcneilly on 11/12/16.
 */
public class CourseAdapter extends WearableListView.Adapter {
    private List<Course> courses;
    private Context context;

    public CourseAdapter(Context context) {
        this.context = context;

        courses = new ArrayList<>();
        courses.add(Course.getTestCourse("Maple Lane East"));
        courses.add(Course.getTestCourse("Maple Lane West"));
        courses.add(Course.getTestCourse("Maple Lane North"));
    }

    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CourseViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_wearable, parent, false));
    }

    @Override
    public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
        ((CourseViewHolder)holder).bind(courses.get(position));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class CourseViewHolder extends WearableListView.ViewHolder {
        private WearableListItem listItem;

        public CourseViewHolder(View view) {
            super(view);

            listItem = (WearableListItem) view;
        }

        public void bind(Course course) {
            listItem.setText(course.getName());
        }
    }
}
