package com.jknoxville.streaky.lib.event;

import java.util.Calendar;

import com.jknoxville.streaky.lib.Streak;

public class LengthStreakCalculator implements StreakCalculator {

    private final Frequency freq;
    private static final StreakType type = StreakType.LENGTH;

    public LengthStreakCalculator(Frequency freq) {
        this.freq = freq;
    }

    @Override
    public Streak getCurrentStreak(EventLog log) {
        Calendar now = Calendar.getInstance();
        return getStreakEndingAt(log, now);

    }
    
    private Streak getStreakEndingAt(EventLog log, Calendar endpoint) {
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
    
    public StreakType getType() {
        return type;
    }
    public int getPeriod() {
        return this.freq.getPeriod();
    }
    public StreakUnit getUnit() {
        return this.freq.getUnit();
    }

    @Override
    public Streak getBestStreak(EventLog log) {
        Calendar startDate = log.startDate;
        Calendar cursor = Calendar.getInstance();
        int bestStreak = 0;
        int currentStreak = 0;
        while(cursor.after(startDate)) {
            if(freq.eventOccursWithinPeriod(log, cursor)) {
                currentStreak++;
            } else {
                bestStreak = currentStreak > bestStreak ? currentStreak : bestStreak;
                currentStreak = 0;
            }
            cursor.add(Calendar.DAY_OF_YEAR, -1);
        }
        bestStreak = currentStreak > bestStreak ? currentStreak : bestStreak;
        
        return new Streak(bestStreak, freq);
    }

    @Override
    public Streak getPreviousStreak(EventLog log) {
        // TODO Auto-generated method stub
        return null;
    }

}
