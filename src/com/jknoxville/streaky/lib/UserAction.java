package com.jknoxville.streaky.lib;

import com.jknoxville.streaky.lib.event.EventLog;
import com.jknoxville.streaky.lib.event.StreakCalculator;

public class UserAction {
	
	private StreakCalculator calculator;
	private EventLog eventLog;
	
	public Streak getCurrentStreak() {
		return calculator.getCurrentStreak(eventLog);
	}
	public Streak getBestStreak() {
		return calculator.getBestStreak(eventLog);
	}
	public Streak getPreviousStreak() {
		return calculator.getPreviousStreak(eventLog);
	}

}
