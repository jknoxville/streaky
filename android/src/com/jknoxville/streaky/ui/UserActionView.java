package com.jknoxville.streaky.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jknoxville.streaky.R;
import com.jknoxville.streaky.lib.Streak;
import com.jknoxville.streaky.lib.UserAction;

public class UserActionView extends LinearLayout {
    
    private final UserAction action;
    private final Context context;
    
    private final float 
        SCALE = getContext().getResources().getDisplayMetrics().density;
    private final int 
        ICON_LENGTH     = dip(20),
        TOP_BAR_PADDING = dip(4),
        ACTION_MARGIN   = dip(10),
        TEXT_COLOR      = Color.parseColor("#4C4C4C");
    
    private static int childIndex = 0;
    private static int TOP_BAR_CHILD_INDEX = childIndex++;
    private static int MAIN_CONTENT_CHILD_INDEX = childIndex++;
    
    @SuppressWarnings("deprecation")
    public UserActionView(Context context, UserAction action) {
        super(context);
        this.action = action;
        this.context = context;
        this.setOrientation(VERTICAL);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, ACTION_MARGIN, 0, ACTION_MARGIN);
        this.setLayoutParams(lp);
        View topBar = getTopBar();
        View mainContent = getMainContent();
        Drawable bg = getResources().getDrawable(R.drawable.dashboard_item);
        this.addView(topBar, TOP_BAR_CHILD_INDEX);
        this.addView(mainContent, MAIN_CONTENT_CHILD_INDEX);
        this.setBackgroundDrawable(bg);
    }
    
    @Override
    public void invalidate() {
        super.invalidate();
        if(this.action != null) {
            removeViewAt(MAIN_CONTENT_CHILD_INDEX);
            addView(getMainContent(), MAIN_CONTENT_CHILD_INDEX);
        }
    }
    
    private View getTopBar() {
        LinearLayout topBar = new LinearLayout(context);
        topBar.setOrientation(HORIZONTAL);
        topBar.setPadding(TOP_BAR_PADDING, TOP_BAR_PADDING, TOP_BAR_PADDING, 0);
        topBar.setGravity(Gravity.CENTER_VERTICAL);
        
        ImageView icon = new ImageView(context);
        Drawable iconDrawable = getResources().getDrawable(R.drawable.running);
        icon.setImageDrawable(iconDrawable);
        int dipIconLength = dip(ICON_LENGTH);
        LayoutParams iconParams = new LayoutParams(dipIconLength, dipIconLength);
        icon.setLayoutParams(iconParams);
        
        TextView nameView = new TextView(context);
        nameView.setText(action.getName());
        nameView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        int namePadding = dip(5);
        nameView.setPadding(namePadding, namePadding, namePadding, 0);
        nameView.setTextColor(TEXT_COLOR);
        
        topBar.addView(icon);
        topBar.addView(nameView);
        return topBar;
    }
    
    private View getMainContent() {
        RelativeLayout mainContent = new RelativeLayout(context);
        int padding = dip(10);
        mainContent.setPadding(padding, 0, padding, padding);
        mainContent.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        
        TextView score = new TextView(context);
        Streak currentStreak = action.getCurrentStreak();
        score.setText(
                currentStreak.amount+" "+
                currentStreak.unit.getString(currentStreak.amount) );
        score.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        score.setTextColor(TEXT_COLOR);
        mainContent.addView(score);
        
        return mainContent;
    }
    
    public UserAction getUserAction() {
        return action;
    }
    
    private int dip(int px) {
        return (int) (px * SCALE + 0.5f);
    }
    
    // TODO: Remove this when placeholder is gone from xml
    public UserActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.action = null;
        this.context = context;
    }

}
