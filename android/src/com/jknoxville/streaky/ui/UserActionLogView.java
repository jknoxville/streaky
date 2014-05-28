package com.jknoxville.streaky.ui;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jknoxville.streaky.lib.UserAction;

public class UserActionLogView extends LinearLayout {

    public UserActionLogView(Context context, UserAction action) {
        super(context);
        this.setOrientation(VERTICAL);
        TextView logText = new TextView(context);
        String str = "";
        for(Boolean occurs: action.getEventLog().getOccurences(50)) {
            str += occurs ? "1" : "0";
        }
        logText.setText("LOG: "+str);
        this.addView(logText);
    }
    
    

}
