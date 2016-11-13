package com.adammcneilly.golftracker;

import android.content.Context;
import android.content.Intent;
import android.support.wearable.view.WearableListView;
import android.support.wearable.view.WearableRecyclerView;
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
public class CourseAdapter extends WearableRecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
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
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CourseViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_wearable, parent, false));
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        holder.bind(courses.get(position));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class CourseViewHolder extends WearableListView.ViewHolder implements View.OnClickListener{
        private WearableListItem listItem;

        public CourseViewHolder(View view) {
            super(view);

            listItem = (WearableListItem) view;
            listItem.setOnClickListener(this);
        }

        public void bind(Course course) {
            listItem.setText(course.getName());
        }

        @Override
        public void onClick(View view) {
            Intent gameActivity = new Intent(context, GameActivity.class);
            gameActivity.putExtra(GameActivity.ARG_COURSE, courses.get(getAdapterPosition()));
            context.startActivity(gameActivity);
        }
    }
}
