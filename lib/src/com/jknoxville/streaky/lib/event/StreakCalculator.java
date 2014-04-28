package com.jknoxville.streaky.lib.event;

import com.jknoxville.streaky.lib.Streak;

public interface StreakCalculator {
	
	public Streak getCurrentStreak(EventLog log);
	public Streak getBestStreak(EventLog log);
	public Streak getPreviousStreak(EventLog log);

}
