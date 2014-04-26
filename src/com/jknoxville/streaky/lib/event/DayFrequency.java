package com.jknoxville.streaky.lib.event;

import java.util.Calendar;

public class DayFrequency extends Frequency {

	@Override
	public boolean occursWithinPeriod(EventLog log, Calendar timeInsidePeriod) {
		return log.containsEventInDay(
				timeInsidePeriod.get(Calendar.YEAR),
				timeInsidePeriod.get(Calendar.MONTH),
				timeInsidePeriod.get(Calendar.WEEK_OF_MONTH),
				timeInsidePeriod.get(Calendar.DAY_OF_WEEK));
	}

}
