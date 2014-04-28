package com.jknoxville.streaky.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;

public class DashboardItemView extends RelativeLayout {
    
    private int desiredHeight = 150;

    public DashboardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);
        
        int height = Math.min(maxHeight, desiredHeight);
        setMeasuredDimension(maxWidth, height);
    }

}
