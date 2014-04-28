package com.jknoxville.streaky.lib.event;

import java.util.Calendar;

import com.jknoxville.streaky.lib.Streak;

public class LengthStreakCalculator implements StreakCalculator {

    private final Frequency freq;

    public LengthStreakCalculator(Frequency freq) {
        this.freq = freq;
    }

    @Override
    public Streak getCurrentStreak(EventLog log) {
        Calendar now = Calendar.getInstance();
        boolean hasLatestEventBeenDone = freq.eventOccursWithinPeriod(log, now);
        
        int streakLength = hasLatestEventBeenDone ? 1 : 0;
        boolean inStreak = true;
        Calendar instant = now;
        freq.setToPreviousPeriod(instant);
        while(inStreak && instant.after(log.getStartDate())) {
            if(freq.eventOccursWithinPeriod(log, instant)) {
                streakLength++;
            } else {
                break;
            }
            freq.setToPreviousPeriod(instant);
        }
        return new Streak(streakLength, freq);
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
