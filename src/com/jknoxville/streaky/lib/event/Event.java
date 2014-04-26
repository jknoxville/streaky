package com.jknoxville.streaky.lib.event;

import java.util.Calendar;

public class Event implements Comparable<Event> {
	
	private final Calendar calendar;
	
	public Event() {
		calendar = Calendar.getInstance();
	}

	@Override
	public int compareTo(Event other) {
		return this.calendar.compareTo(other.calendar);
	}
	
	Calendar getCalendar() {
		return calendar;
	}
	
	public boolean isSameDayAs(Event otherEvent) {
		return (this.calendar.YEAR == otherEvent.calendar.YEAR) &&
			   (this.calendar.DAY_OF_YEAR == otherEvent.calendar.DAY_OF_YEAR);
	}

}
