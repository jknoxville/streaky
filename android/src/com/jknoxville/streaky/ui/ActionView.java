package com.jknoxville.streaky.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jknoxville.streaky.R;

public class ActionView extends LinearLayout {
    
    private Context context;
    private AttributeSet attrs;
    private String title;
    private int currentStreak, bestStreak;
    
    private LinearLayout root;
    private LinearLayout topBar;
    private TextView titleText;
    
    private RelativeLayout content;
    private TextView currentStreakText;
    private TextView bestStreakText;

    public ActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
        
        loadAttrs();
        inflateLayout();
        updateChildViews();
    }
    
    private void loadAttrs() {
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
