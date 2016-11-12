package com.adammcneilly.golftracker;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.GridViewPager;
import android.util.Log;

import com.adammcneilly.golftracker.utility.models.Game;
import com.adammcneilly.golftracker.utility.models.Hole;
import com.adammcneilly.golftracker.adapters.HoleAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.google.gson.Gson;

public class MainActivity extends WearableActivity implements HoleAdapter.OnGameCompletedListener{
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle connectionHint) {
                        Log.d(LOG_TAG, "onConnected: " + connectionHint);
                        // Now you can use the Data Layer API
                    }
                    @Override
                    public void onConnectionSuspended(int cause) {
                        Log.d(LOG_TAG, "onConnectionSuspended: " + cause);
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult result) {
                        Log.d(LOG_TAG, "onConnectionFailed: " + result);
                    }
                })
                // Request access only to the Wearable API
                .addApi(Wearable.API)
                .build();

        GridViewPager gridViewPager = (GridViewPager) findViewById(R.id.view_pager);
        HoleAdapter holeAdapter = new HoleAdapter(getFragmentManager(), Game.getPar3Game());
        holeAdapter.setOnGameCompletedListener(this);
        gridViewPager.setAdapter(holeAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        googleApiClient.connect();
    }

    @Override
    protected void onPause() {
        googleApiClient.disconnect();
        super.onPause();
    }

    @Override
    public void onGameCompleted(Game game) {
        Log.v(LOG_TAG, "Game completed.");
        for(Hole hole : game.getHoles()) {
            Log.v(LOG_TAG, "Hole: " + hole.getNumber() + " score: " + hole.getScore());
        }

        final String gameString = new Gson().toJson(game);
        Wearable.NodeApi.getConnectedNodes(googleApiClient)
                .setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
                    @Override
                    public void onResult(NodeApi.GetConnectedNodesResult nodes) {
                        for (Node node : nodes.getNodes()) {
                            Log.v(LOG_TAG, "Sending:  " + node.getId());
                            Wearable.MessageApi.sendMessage(
                                    googleApiClient, node.getId(), "/game", gameString.getBytes()).setResultCallback(

                                    new ResultCallback<MessageApi.SendMessageResult>() {
                                        @Override
                                        public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                                            if (!sendMessageResult.getStatus().isSuccess()) {
                                                Log.e(LOG_TAG, "Failed to send message with status code: "
                                                        + sendMessageResult.getStatus().getStatusCode());
                                            } else {
                                                Log.v(LOG_TAG, "Message sent.");
                                                finish();
                                            }
                                        }
                                    }
                            );
                        }
                    }
                });
    }
}
