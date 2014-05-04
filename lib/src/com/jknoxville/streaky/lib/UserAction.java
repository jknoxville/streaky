package com.jknoxville.streaky.lib;

import java.util.Calendar;
import java.util.Comparator;

import com.jknoxville.streaky.lib.event.EventLog;
import com.jknoxville.streaky.lib.event.StreakCalculator;

public class UserAction implements Comparable<UserAction> {
	
	private final StreakCalculator calculator;
	private final EventLog eventLog;
	private final int id;
	private String name;
	
	// TODO: pass in eventlog to constructor
	public UserAction(String name, StreakCalculator calc, int id) {
	    this.name = name;
	    this.eventLog = new EventLog();
	    this.calculator = calc;
	    this.id = id;
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
	
	public void newEvent() {
	    eventLog.addEvent(Calendar.getInstance());
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
