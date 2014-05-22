package com.jknoxville.streaky.lib;

import java.util.Calendar;
import java.util.Comparator;

import com.jknoxville.streaky.lib.event.EventLog;
import com.jknoxville.streaky.lib.event.StreakCalculator;
import com.jknoxville.streaky.lib.event.StreakUnit;

public class UserAction implements Comparable<UserAction> {
	
	private final StreakCalculator calculator;
	private final EventLog eventLog;
	private final int id;
	private String name;
	private final Calendar creationDate;
	
	// TODO: pass in eventlog to constructor
	public UserAction(String name, StreakCalculator calc, int id, Calendar creationDate) {
	    this.name = name;
	    this.calculator = calc;
	    this.id = id;
	    this.creationDate = creationDate != null ? creationDate : Calendar.getInstance();
	    this.eventLog = new EventLog(this.creationDate);
	}
	
	public Streak getCurrentStreak() {
		return calculator.getCurrentStreak(eventLog);
	}
	public Streak getBestStreak() {
		return calculator.getBestStreak(eventLog);
	}
	public Streak getPreviousStreak() {
		return calculator.getPreviousStreak(eventLog);
	}

	public String getName() {
	    return name;
	}
	public int getID() {
	    return id;
	}
	public Calendar getCreationDate() {
	    return creationDate;
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
