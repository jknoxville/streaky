package com.jknoxville.streaky.lib;

import com.jknoxville.streaky.lib.event.TimePeriod;
import com.jknoxville.streaky.lib.event.StreakUnit;

public class Streak implements Comparable<Streak> {
    
    public final StreakUnit unit;
    public final int amount;
    
    public Streak(int amount, TimePeriod freq) {
        this.unit = freq.getUnit();
        this.amount = amount;
    }

    @Override
    public int compareTo(Streak other) {
        return -(Integer.valueOf(this.amount).compareTo(Integer.valueOf(other.amount)));
    }
    
    public String toString() {
        return unit.getString(amount);
    }
    
}
