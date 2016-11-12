package com.adammcneilly.golftracker;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;

import com.adammcneilly.golftracker.utility.models.Game;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import com.google.gson.Gson;

/**
 * Service to listen for Android Wear messages.
 *
 * Created by adam.mcneilly on 11/12/16.
 */
public class ListenerService extends WearableListenerService {

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if(messageEvent.getPath().equals("/game")) {
            String gameJson = new String(messageEvent.getData());
            Game game = new Gson().fromJson(gameJson, Game.class);

            NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Game completed!")
                    .setContentText("Your score was: " + game.getScore() + " strokes.");

            // Sets an ID for the notification
            int mNotificationId = 1;
            // Gets an instance of the NotificationManager service
            NotificationManager mNotifyMgr =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // Builds the notification and issues it.
            mNotifyMgr.notify(mNotificationId, mBuilder.build());
        }
    }
}
