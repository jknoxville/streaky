package com.jknoxville.streaky.lib.event;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EventLog {
	
	/*
	 *  Events are keyed by:
	 *  Year -> Month -> Day
	 *  Year -> Week  -> Day
	 */
	Map<Integer, TreeMap<Integer, TreeMap<Integer, List<Event>>>> yearsByMonth;
	Map<Integer, TreeMap<Integer, TreeMap<Integer, List<Event>>>> yearsByWeek;
	protected final Calendar startDate;

	public EventLog() {
		yearsByMonth = new TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, List<Event>>>>();
		yearsByWeek = new TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, List<Event>>>>();
		startDate = Calendar.getInstance();
	}
	
	public void addEvent(Calendar cal) {
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH);
	    int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
	    int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
	    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	    if(!yearsByMonth.containsKey(year)) {
	        yearsByMonth.put(year, new TreeMap<Integer, TreeMap<Integer, List<Event>>>());
	    }
	    if(!yearsByWeek.containsKey(year)) {
            yearsByWeek.put(year, new TreeMap<Integer, TreeMap<Integer, List<Event>>>());
        }
	    Map<Integer, TreeMap<Integer, List<Event>>> monthMap = yearsByMonth.get(year);
	    Map<Integer, TreeMap<Integer, List<Event>>> weekMap = yearsByWeek.get(year);
	    if(!monthMap.containsKey(month)) {
	        monthMap.put(month, new TreeMap<Integer, List<Event>>());
	    }
	    if(!weekMap.containsKey(weekOfYear)) {
	        weekMap.put(weekOfYear, new TreeMap<Integer, List<Event>>());
	    }
	    Map<Integer, List<Event>> daysInMonth = monthMap.get(month);
	    Map<Integer, List<Event>> daysInWeek = weekMap.get(weekOfYear);
	    List<Event> daysEvents;
	    if(daysInMonth.containsKey(dayOfMonth) && daysInWeek.containsKey(dayOfWeek)) {
	        daysEvents = daysInWeek.get(dayOfWeek);
	    } else {
            daysEvents = new LinkedList<Event>();
	        daysInMonth.put(dayOfMonth, daysEvents);
            daysInWeek.put(dayOfWeek, daysEvents);
        }
	    
	    daysEvents.add(new Event(cal));
	    
	}

	public boolean containsEventInYear(int year) {
		return yearsByMonth.get(year) != null;
	}
	public boolean containsEventInMonth(int year, int month) {
		return this.containsEventInYear(year) && yearsByMonth.get(year).get(month) != null;
	}
	public boolean containsEventInWeek(int year, int week) {
		return this.containsEventInYear(year) && yearsByWeek.get(year).get(week) != null;
	}
	public boolean containsEventInDay(int year, int week, int day) {
		return this.containsEventInWeek(year, week) &&
				yearsByWeek.get(year).get(week).get(day) != null;
	}
	
	public Calendar getStartDate() {
	    return startDate;
	}

}
