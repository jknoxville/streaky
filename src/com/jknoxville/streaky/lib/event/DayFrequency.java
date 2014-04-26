package com.jknoxville.streaky.lib.event;

import java.util.Calendar;

public class DayFrequency extends Frequency {
    
    private static final int NUM_DAYS = 1;
    private static final StreakUnit unit = StreakUnit.DAY;

	@Override
	public boolean occursWithinPeriod(EventLog log, Calendar timeInsidePeriod) {
		return log.containsEventInDay(
				timeInsidePeriod.get(Calendar.YEAR),
				timeInsidePeriod.get(Calendar.MONTH),
				timeInsidePeriod.get(Calendar.WEEK_OF_MONTH),
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

}
