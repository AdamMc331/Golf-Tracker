package com.adammcneilly.golftracker.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.wearable.view.ActionPage;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adammcneilly.golftracker.R;

/**
 * Fragment that allows a user to Submit their game.
 *
 * Created by adam.mcneilly on 11/12/16.
 */
public class SubmitFragment extends Fragment {

    private OnSubmitListener onSubmitListener;

    public static SubmitFragment newInstance() {
        Bundle args = new Bundle();

        SubmitFragment fragment = new SubmitFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submit, container, false);

        ActionPage actionPage = (ActionPage) view.findViewById(R.id.action_page);
        actionPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onSubmitListener != null) {
                    onSubmitListener.onSubmit();
                }
            }
        });

        return view;
    }

    public void setOnSubmitListener(OnSubmitListener listener) {
        this.onSubmitListener = listener;
    }

    public interface OnSubmitListener {
        void onSubmit();
    }
}
