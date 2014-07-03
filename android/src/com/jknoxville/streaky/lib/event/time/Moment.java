package com.jknoxville.streaky.lib.event.time;


public interface Moment {
    
    public int getAbsoluteTimeInPeriod(TimePeriod2 period);
    
    public Moment getPreviousMoment(TimePeriod2 period);
    public Moment getNextMoment(TimePeriod2 period);
    
}
