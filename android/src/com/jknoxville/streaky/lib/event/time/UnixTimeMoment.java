package com.jknoxville.streaky.lib.event.time;

import com.jknoxville.streaky.error.OutOfBoundsException;


public class UnixTimeMoment implements Moment {
    
    private long unixTime;
    
    public UnixTimeMoment(long unixTime) {
        this.unixTime = unixTime;
    }

    @Override
    public long getAbsoluteTimeInPeriod(TimePeriod2 period) {
        return period.getPeriodInstanceFromTimeInMillis(unixTime);
    }

    @Override
    public Moment getPreviousMoment(TimePeriod2 period) throws OutOfBoundsException {
        long newTime = period.addNPeriodsTo(-1, unixTime);
        return new UnixTimeMoment(newTime);
    }

    @Override
    public Moment getNextMoment(TimePeriod2 period) throws OutOfBoundsException {
        long newTime = period.addNPeriodsTo(1, unixTime);
        return new UnixTimeMoment(newTime);
    }

    @Override
    public int compareTo(Moment otherMoment) {
        int comparisonOutcome = 0;
        for (TimePeriod2 period: TimePeriod2.values()) {
            if (this.getAbsoluteTimeInPeriod(period) < otherMoment.getAbsoluteTimeInPeriod(period)) {
                comparisonOutcome = -1;
                break;
            } else if (this.getAbsoluteTimeInPeriod(period) > otherMoment.getAbsoluteTimeInPeriod(period)) {
                comparisonOutcome = 1;
                break;
            }
        }
        return comparisonOutcome;
    }
    
    @Override
    public String toString() {
        return String.valueOf(unixTime);
    }

}
