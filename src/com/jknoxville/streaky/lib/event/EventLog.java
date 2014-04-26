package com.jknoxville.streaky.lib.event;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EventLog {
	
	// Event log keyed by Year -> Month -> Week -> Day
	Map<Integer, TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, List<Event>>>>> years;
	protected final Calendar startDate;

	public EventLog() {
		years = new TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, List<Event>>>>>();
		startDate = Calendar.getInstance();
	}
	
	protected void addEvent(Calendar cal) {
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH);
	    int week = cal.get(Calendar.WEEK_OF_MONTH);
	    int day = cal.get(Calendar.DAY_OF_WEEK);
	    if(!years.containsKey(year)) {
	        years.put(year, new TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, List<Event>>>>());
	    }
	    Map<Integer, TreeMap<Integer, TreeMap<Integer, List<Event>>>> monthMap = years.get(year);
	    if(!monthMap.containsKey(month)) {
	        monthMap.put(month, new TreeMap<Integer, TreeMap<Integer, List<Event>>>());
	    }
	    Map<Integer, TreeMap<Integer, List<Event>>> weekMap = monthMap.get(month);
	    if(!weekMap.containsKey(week)) {
	        weekMap.put(week, new TreeMap<Integer, List<Event>>());
	    }
	    Map<Integer, List<Event>> dayMap = weekMap.get(week);
	    if(!dayMap.containsKey(day)) {
	        dayMap.put(day, new LinkedList<Event>());
	    }
	    List<Event> daysEvents = dayMap.get(day);
	    daysEvents.add(new Event(cal));
	    
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
