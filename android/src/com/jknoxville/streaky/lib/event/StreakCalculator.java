package com.jknoxville.streaky.lib.event;

import java.util.Calendar;

import com.jknoxville.streaky.lib.Streak;

public interface StreakCalculator {
	
	public Streak getCurrentStreak(EventLog log, Calendar now);
	public Streak getBestStreak(EventLog log, Calendar now);
	public Streak getPreviousStreak(EventLog log, Calendar now);
	public StreakType getType();
	public int getPeriod();
	public StreakUnit getUnit();
	
	public enum StreakType {
	    LENGTH;
	}

}
