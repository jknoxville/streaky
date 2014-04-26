package com.jknoxville.streaky.lib.event;

import java.util.Calendar;

/*
 * Implementations of this class are used to determine the frequency at which
 * events are required to continue a streak.
 */
public abstract class Frequency {

    public abstract boolean occursWithinPeriod(EventLog log, Calendar timeInsidePeriod);
    public abstract void setToPreviousPeriod(Calendar cal);
    public abstract StreakUnit getUnit();

}
