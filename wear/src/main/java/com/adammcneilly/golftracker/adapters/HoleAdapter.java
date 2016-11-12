package com.adammcneilly.golftracker.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.wearable.view.FragmentGridPagerAdapter;

import com.adammcneilly.golftracker.utility.models.Game;
import com.adammcneilly.golftracker.fragments.HoleFragment;
import com.adammcneilly.golftracker.fragments.SubmitFragment;

/**
 * Adapter that displays a list of holes for a game.
 *
 * Created by adam.mcneilly on 11/12/16.
 */
public class HoleAdapter extends FragmentGridPagerAdapter implements SubmitFragment.OnSubmitListener {
    private Game game;

    private OnGameCompletedListener onGameCompletedListener;

    public HoleAdapter(FragmentManager fragmentManager, Game game) {
        super(fragmentManager);

        this.game = game;
    }

    @Override
    public Fragment getFragment(int row, int col) {
        if(col < game.getHoles().size()) {
            return HoleFragment.newInstance(game.getHole(col));
        } else {
            SubmitFragment submitFragment = SubmitFragment.newInstance();
            submitFragment.setOnSubmitListener(this);
            return submitFragment;
        }
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount(int i) {
        return game.getHoles().size() + 1;
    }

    @Override
    public void onSubmit() {
        if(onGameCompletedListener != null) {
            onGameCompletedListener.onGameCompleted(game);
        }
    }

    public void setOnGameCompletedListener(OnGameCompletedListener listener) {
        this.onGameCompletedListener = listener;
    }

    public interface OnGameCompletedListener {
        void onGameCompleted(Game game);
    }
}
