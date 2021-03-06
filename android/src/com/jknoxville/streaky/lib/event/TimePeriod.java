package com.jknoxville.streaky.lib.event;

import java.util.Calendar;

/*
 * Implementations of this class are used to determine the frequency at which
 * events are required to continue a streak.
 */
public abstract class TimePeriod {

    public abstract boolean eventOccursWithinPeriod(EventLog log, Calendar timeInsidePeriod);
    public abstract void setToPreviousPeriod(Calendar cal);
    public abstract StreakUnit getUnit();
    public abstract int getPeriod();
    public abstract boolean isInSameOrLaterPeriod(Calendar thisCal, Calendar thatCal);

}
