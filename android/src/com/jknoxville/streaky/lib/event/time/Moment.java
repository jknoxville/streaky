package com.jknoxville.streaky.lib.event.time;

import com.jknoxville.streaky.error.OutOfBoundsException;

public interface Moment extends Comparable<Moment> {
    
    public long getAbsoluteTimeInPeriod(TimePeriod2 period);
    
    public Moment getPreviousMoment(TimePeriod2 period) throws OutOfBoundsException;
    public Moment getNextMoment(TimePeriod2 period) throws OutOfBoundsException;
    
}
