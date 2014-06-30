package com.jknoxville.streaky.lib.event;

import java.util.Calendar;

import com.jknoxville.streaky.lib.Streak;

public class LengthStreakCalculator implements StreakCalculator {

    private final TimePeriod period;
    private static final StreakType type = StreakType.LENGTH;

    public LengthStreakCalculator(TimePeriod period) {
        this.period = period;
    }

    @Override
    public Streak getCurrentStreak(EventLog log, Calendar now) {
        return getStreakEndingAt(log, now);

    }
    
    private Streak getStreakEndingAt(EventLog log, Calendar endpoint) {
        boolean hasLatestEventBeenDone = period.eventOccursWithinPeriod(log, endpoint);
        
        int streakLength = hasLatestEventBeenDone ? 1 : 0;
        boolean inStreak = true;
        Calendar instant = endpoint;
        period.setToPreviousPeriod(instant);
        while(inStreak && isInSameOrLaterDay(instant, log.startDate)) {
            if(period.eventOccursWithinPeriod(log, instant)) {
                streakLength++;
            } else {
                break;
            }
            period.setToPreviousPeriod(instant);
        }
        return new Streak(streakLength, period);
    }
    
    public StreakType getType() {
        return type;
    }
    public int getPeriod() {
        return this.period.getPeriod();
    }
    public StreakUnit getUnit() {
        return this.period.getUnit();
    }
    
    private boolean isInSameOrLaterDay(Calendar thisCal, Calendar thatCal) {
        return thisCal.get(Calendar.YEAR) > thatCal.get(Calendar.YEAR)
               || ( thisCal.get(Calendar.YEAR) == thatCal.get(Calendar.YEAR)
                    && thisCal.get(Calendar.DAY_OF_YEAR) >= thatCal.get(Calendar.DAY_OF_YEAR) );
    }

    @Override
    public Streak getBestStreak(EventLog log, Calendar now) {
        Calendar startDate = log.startDate;
        int bestStreak = 0;
        int currentStreak = 0;
        while(now.after(startDate)) {
            if(period.eventOccursWithinPeriod(log, now)) {
                currentStreak++;
            } else {
                bestStreak = currentStreak > bestStreak ? currentStreak : bestStreak;
                currentStreak = 0;
            }
            now.add(Calendar.DAY_OF_YEAR, -1);
        }
        bestStreak = currentStreak > bestStreak ? currentStreak : bestStreak;
        
        return new Streak(bestStreak, period);
    }

    @Override
    public Streak getPreviousStreak(EventLog log, Calendar now) {
        // TODO Auto-generated method stub
        return null;
    }

}
