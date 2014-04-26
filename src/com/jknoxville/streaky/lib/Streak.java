package com.jknoxville.streaky.lib;

import com.jknoxville.streaky.lib.event.Frequency;
import com.jknoxville.streaky.lib.event.StreakUnit;

public class Streak {
    
    public final StreakUnit unit;
    public final int amount;
    
    public Streak(int amount, Frequency freq) {
        this.unit = freq.getUnit();
        this.amount = amount;
    }
}
