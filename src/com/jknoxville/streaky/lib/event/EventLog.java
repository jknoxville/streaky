package com.jknoxville.streaky.lib.event;

import java.util.Calendar;
import java.util.TreeMap;

public class EventLog {
	
	// Event log keyed by Year -> Month -> Week -> Day
	TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, Event>>>> years;
	private final Calendar startDate;

	public EventLog() {
		years = new TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, Event>>>>();
		startDate = Calendar.getInstance();
	}

	public boolean containsEventInYear(int year) {
		return years.get(year) != null;
	}
	public boolean containsEventInMonth(int year, int month) {
		return this.containsEventInYear(year) && years.get(year).get(month) != null;
	}
	public boolean containsEventInWeek(int year, int month, int week) {
		return this.containsEventInMonth(year, month) && years.get(year).get(month).get(week) != null;
	}
	public boolean containsEventInDay(int year, int month, int week, int day) {
		return this.containsEventInWeek(year, month, week) &&
				years.get(year).get(month).get(week).get(day) != null;
	}
	
	public Calendar getStartDate() {
	    return startDate;
	}

}
