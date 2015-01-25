package com.meep.whatsthisword;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.google.android.glass.widget.CardBuilder;

/**
 * Created by Camille on 1/25/15.
 */
public class DisplayWordActivity extends Activity {
    private Bundle extras = getIntent().getExtras();
    private String getSpokenText(){
        String spokenText = "";
        if (extras != null) {
            spokenText = extras.getString("spokenText");
        }
        return spokenText;
    }
    View view = new CardBuilder(this, CardBuilder.Layout.TEXT)
            .setText(getSpokenText())
            .setTimestamp("just now")
            .getView();
}
