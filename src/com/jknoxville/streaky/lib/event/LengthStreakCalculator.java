package com.jknoxville.streaky.lib.event;

import java.util.Calendar;

import com.jknoxville.streaky.lib.Streak;

public class LengthStreakCalculator implements StreakCalculator {

	@Override
	public Streak getCurrentStreak(EventLog log) {
		Event nowEvent = new Event();
		boolean isTodaysEventDone = nowEvent.isSameDayAs(log.events.peek());
		int streak = isTodaysEventDone ? 1 : 0;
		for(Event e: log.events) {
		}
		return null;
	}

	@Override
	public Streak getBestStreak(EventLog log) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Streak getPreviousStreak(EventLog log) {
		// TODO Auto-generated method stub
		return null;
	}

}
