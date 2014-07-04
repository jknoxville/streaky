package com.jknoxville.streaky.lib.event.time;

import com.jknoxville.streaky.error.OutOfBoundsException;

public abstract class PeriodInstanceCalculator {
    
    public abstract int getPeriodInstanceFromTimeInMillis(long milliseconds);
    abstract long addNPeriodsTo(int numPeriods, long milliseconds);
    
    public long addNPeriodsToAndCheckBounds(int numPeriods, long milliseconds) throws OutOfBoundsException {
        long newMillis = addNPeriodsTo(numPeriods, milliseconds);
        
        if ((numPeriods > 0 && newMillis <= milliseconds) || (numPeriods < 0 && milliseconds <= newMillis)) {
            throw new OutOfBoundsException();
        }
        
        return newMillis;
    }

}
