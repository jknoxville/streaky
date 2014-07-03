package com.jknoxville.streaky.lib.event.time;


public class UnixTimeMoment implements Moment {
    
    private long unixTime;
    
    public UnixTimeMoment(long unixTime) {
        this.unixTime = unixTime;
    }

    @Override
    public int getAbsoluteTimeInPeriod(TimePeriod2 period) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Moment getPreviousMoment(TimePeriod2 period) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Moment getNextMoment(TimePeriod2 period) {
        // TODO Auto-generated method stub
        return null;
    }

}
