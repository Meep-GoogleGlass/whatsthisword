package com.meep.whatsthisword;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.google.android.glass.widget.CardBuilder;

public class DisplayWordActivity extends Activity {
    private Bundle extras = getIntent().getExtras();
    public static String spokenText;

    public String getSpokenText(){
        spokenText = "";
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
