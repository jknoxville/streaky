package com.jknoxville.streaky.lib.event;

import java.util.Calendar;

public class Day extends TimePeriod {
    
    private static final int NUM_DAYS = 1;
    private static final StreakUnit unit = StreakUnit.DAY;

	@Override
	public boolean eventOccursWithinPeriod(EventLog log, Calendar timeInsidePeriod) {
		return log.containsEventInDay(
				timeInsidePeriod.get(Calendar.YEAR),
				timeInsidePeriod.get(Calendar.WEEK_OF_YEAR),
				timeInsidePeriod.get(Calendar.DAY_OF_WEEK));
	}

    @Override
    public void setToPreviousPeriod(Calendar cal) {
        cal.add(Calendar.DAY_OF_MONTH, -NUM_DAYS);
    }

    @Override
    public StreakUnit getUnit() {
        return unit;
    }
    public int getPeriod() {
        return NUM_DAYS;
    }

    @Override
    public boolean isInSameOrLaterPeriod(Calendar thisCal, Calendar thatCal) {
        return thisCal.get(Calendar.YEAR) > thatCal.get(Calendar.YEAR)
                || ( thisCal.get(Calendar.YEAR) == thatCal.get(Calendar.YEAR)
                     && thisCal.get(Calendar.DAY_OF_YEAR) >= thatCal.get(Calendar.DAY_OF_YEAR) );
    }

}
