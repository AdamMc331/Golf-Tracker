package com.adammcneilly.golftracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;

public class CourseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        WearableListView listView = (WearableListView) findViewById(R.id.wearable_list_view);
        CourseAdapter courseAdapter = new CourseAdapter(this);
        listView.setAdapter(courseAdapter);
    }
}
