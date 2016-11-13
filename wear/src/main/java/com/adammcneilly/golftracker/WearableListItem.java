package com.adammcneilly.golftracker;

import android.content.Context;
import android.os.Build;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Custom item layout that enlarges the centered text.
 *
 * Created by adam.mcneilly on 11/12/16.
 */
public class WearableListItem extends TextView implements WearableListView.OnCenterProximityListener {

    public WearableListItem(Context context) {
        this(context, null);
    }

    public WearableListItem(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WearableListItem(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
    }

    @Override
    public void onCenterPosition(boolean b) {
        // When centered, let's enlarge the text.
        // As of API 23, we don't need to pass context.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setTextAppearance(android.R.style.TextAppearance_Large);
        } else {
            setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
        }
    }

    @Override
    public void onNonCenterPosition(boolean b) {
        // When leaving centered, set text backk to small.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setTextAppearance(android.R.style.TextAppearance_Small);
        } else {
            setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
        }
    }
}