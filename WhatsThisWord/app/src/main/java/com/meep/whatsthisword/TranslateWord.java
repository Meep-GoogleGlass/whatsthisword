package com.meep.whatsthisword;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.LiveCard.PublishMode;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.net.URLEncoder;


/**
 * A {@link Service} that publishes a {@link LiveCard} in the timeline.
 */
public class TranslateWord extends Service {

    private static final String LIVE_CARD_TAG = "TranslateWord";

    private LiveCard mLiveCard;

    String url = "https://www.googleapis.com/language/translate/v2";
    String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
    String key = APIkey;
    String q = getSpokenText();
    String target = "en";
// ...

    String query = String.format("param1=%s&param2=%s",
            URLEncoder.encode(param1, charset),
            URLEncoder.encode(param2, charset));

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mLiveCard == null) {
            mLiveCard = new LiveCard(this, LIVE_CARD_TAG);

            LiveCardRenderer renderer = new LiveCardRenderer(this);
            mLiveCard.setDirectRenderingEnabled(true).getSurfaceHolder().addCallback(renderer);

            // Display the options menu when the live card is tapped.
            Intent menuIntent = new Intent(this, LiveCardMenuActivity.class);
            mLiveCard.setAction(PendingIntent.getActivity(this, 0, menuIntent, 0));
            mLiveCard.attach(this);
            mLiveCard.publish(PublishMode.REVEAL);
        } else {
            mLiveCard.navigate();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mLiveCard != null && mLiveCard.isPublished()) {
            mLiveCard.unpublish();
            mLiveCard = null;
        }
        super.onDestroy();
    }

    public String translate(String toTranslate){
        //Todo: implement translation.
        return toTranslate;
    }
}
