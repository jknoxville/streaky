package com.jknoxville.streaky.ui;

import com.jknoxville.streaky.R;

public enum ActivityIcon {
    
    BASKETBALL(R.drawable.basketball3),
    CLIMBING(R.drawable.climbing5),
    EXERCIZE(R.drawable.exercise1),
    FOOTBALL(R.drawable.football118),
    MEDAL(R.drawable.football78),
    GOLF(R.drawable.golf22),
    YOGA(R.drawable.man271),
    FRISBEE(R.drawable.man362),
    ROWING(R.drawable.person209),
    SWIMMING(R.drawable.person228),
    FIGHTING(R.drawable.person243),
    CYCLING(R.drawable.regular2),
    WALKING(R.drawable.silhouette4),
    SKIING(R.drawable.skiing),
    TREKKING(R.drawable.trekking),
    VOLLEYBALL(R.drawable.volleyball3),
    WEIGHTLIFTING(R.drawable.weightlift),
    BALANCING(R.drawable.yoga12),
    RUNNING(R.drawable.running);
    
    public final int drawableId;
    
    private ActivityIcon(int drawableId) {
        this.drawableId = drawableId;
    }

}
