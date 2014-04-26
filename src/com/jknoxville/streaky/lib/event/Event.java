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

}
