package com.jknoxville.streaky.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jknoxville.streaky.R;
import com.jknoxville.streaky.lib.UserAction;

public class ActionView extends LinearLayout {
    
    private Context context;
    private UserAction action;
    
    private String title;
    private int currentStreak, bestStreak;
    
    private LinearLayout root;
    private LinearLayout topBar;
    private TextView titleText;
    
    private RelativeLayout content;
    private TextView currentStreakText;
    private TextView bestStreakText;
    
    public ActionView(Context context, UserAction action) {
        super(context);
        this.context = context;
        this.action = action;
        
        loadAttrs(action);
        inflateLayout();
        updateChildViews();
    }

    // Only for design process. Needs to be initialized with a UserAction to be actually useful
    public ActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        
        loadAttrs(attrs);
        inflateLayout();
        updateChildViews();
    }
    
    public UserAction getUserAction() throws Exception {
        if(action != null) {
            return action;
        } else {
            throw new Exception("No user action");
        }
    }
    
    private void loadAttrs(UserAction action) {
        title = action.getName();
        currentStreak = action.getCurrentStreak().amount;
        bestStreak = action.getBestStreak().amount;
    }
    
    private void loadAttrs(AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ActionView, 0, 0);
        title = a.getString(R.styleable.ActionView_actionName);
        currentStreak = a.getInt(R.styleable.ActionView_currentStreak, 0);
        bestStreak = a.getInt(R.styleable.ActionView_bestStreak, 0);
        a.recycle();
    }
    
    private void inflateLayout() {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_action, this, true);
        
        root = (LinearLayout) getChildAt(0);
        topBar = (LinearLayout) root.getChildAt(0);
        titleText = (TextView) topBar.getChildAt(1);
        
        content = (RelativeLayout) root.getChildAt(1);
        currentStreakText = (TextView) content.getChildAt(0);
        bestStreakText = (TextView) content.getChildAt(1);
    }
    
    private void updateChildViews() {
        titleText.setText(title);
        currentStreakText.setText(currentStreak+" Days");
        bestStreakText.setText(bestStreak+" Days");
    }

}
