package com.jknoxville.streaky.lib;

import java.util.Calendar;
import java.util.Comparator;

import com.jknoxville.streaky.lib.event.EventLog;
import com.jknoxville.streaky.lib.event.StreakCalculator;
import com.jknoxville.streaky.lib.event.StreakUnit;
import com.jknoxville.streaky.ui.ActivityIcon;

public class UserAction implements Comparable<UserAction> {
	
	private final StreakCalculator calculator;
	private final EventLog eventLog;
	private final int id;
	private String name;
	private ActivityIcon icon;
	
	// TODO: pass in eventlog to constructor
	public UserAction(String name, StreakCalculator calc, int id, Calendar creationDate, ActivityIcon icon) {
	    this.name = name;
	    this.calculator = calc;
	    this.id = id;
	    Calendar startDate = creationDate != null ? creationDate : Calendar.getInstance();
	    this.eventLog = new EventLog(startDate);
	    this.icon = icon;
	}
	
	public Streak getCurrentStreak() {
		return calculator.getCurrentStreak(eventLog, Calendar.getInstance());
	}
	public Streak getBestStreak() {
		return calculator.getBestStreak(eventLog, Calendar.getInstance());
	}
	public Streak getPreviousStreak() {
		return calculator.getPreviousStreak(eventLog, Calendar.getInstance());
	}

	public String getName() {
	    return name;
	}
	public int getID() {
	    return id;
	}
	public Calendar getCreationDate() {
	    return eventLog.getStartDate();
	}
	public StreakCalculator.StreakType getStreakType() {
	    return this.calculator.getType();
	}
	public int getStreakPeriod() {
	    return this.calculator.getPeriod();
	}
	public StreakUnit getStreakUnit() {
	    return this.calculator.getUnit();
	}
	public ActivityIcon getIcon() {
	    return this.icon;
	}
	
	public Calendar newEvent() {
	    Calendar now = Calendar.getInstance();
	    eventLog.addEvent(now);
	    return now;
	}
	
	public void addEvent(Calendar cal) {
	    eventLog.addEvent(cal);
	}
	
	public EventLog getEventLog() {
	    return eventLog;
	}

	@Override
    public int compareTo(UserAction other) {
        return this.getCurrentStreak().compareTo(other.getCurrentStreak());
    }

	public static class UserActionPriorityComparator implements Comparator<UserAction> {
        @Override
        public int compare(UserAction a1, UserAction a2) {
            return a2.compareTo(a1);
        }
	}

}
