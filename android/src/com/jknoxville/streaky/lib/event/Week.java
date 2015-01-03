package com.jknoxville.streaky.lib.event;

import java.util.Calendar;

public class Week extends TimePeriod {
    
    private static final int NUM_WEEKS = 1;
    private static final StreakUnit unit = StreakUnit.WEEK;

	@Override
	public boolean eventOccursWithinPeriod(EventLog log, Calendar timeInsidePeriod) {
		return log.containsEventInWeek(
				timeInsidePeriod.get(Calendar.YEAR),
				timeInsidePeriod.get(Calendar.WEEK_OF_YEAR));
	}

    @Override
    public void setToPreviousPeriod(Calendar cal) {
        cal.add(Calendar.WEEK_OF_YEAR, -NUM_WEEKS);
    }

    @Override
    public StreakUnit getUnit() {
        return unit;
    }
    public int getPeriod() {
        return NUM_WEEKS;
    }

    @Override
    public boolean isInSameOrLaterPeriod(Calendar thisCal, Calendar thatCal) {
        return thisCal.get(Calendar.YEAR) > thatCal.get(Calendar.YEAR)
                || ( thisCal.get(Calendar.YEAR) == thatCal.get(Calendar.YEAR)
                     && thisCal.get(Calendar.WEEK_OF_YEAR) >= thatCal.get(Calendar.WEEK_OF_YEAR) );
    }

}
