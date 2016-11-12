package com.adammcneilly.golftracker.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.adammcneilly.golftracker.utility.models.Hole;
import com.adammcneilly.golftracker.R;

/**
 * Fragment for displaying the current hole of the Game.
 *
 * Created by adam.mcneilly on 11/12/16.
 */
public class HoleFragment extends Fragment {
    private static final String ARG_HOLE = "holeArgument";

    private Hole hole;

    public static HoleFragment newInstance(Hole hole) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_HOLE, hole);

        HoleFragment fragment = new HoleFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.hole = getArguments().getParcelable(ARG_HOLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hole, container, false);

        TextView holeNumber = (TextView) view.findViewById(R.id.hole_number);
        String holeText = String.format(getString(R.string.hole_number), hole.getNumber());
        holeNumber.setText(holeText);

        NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.hole_score);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                hole.setScore(newValue);
            }
        });

        return view;
    }
}
