package com.jknoxville.streaky.lib.event.time;


public enum TimePeriod2 {
    
    DAY(new DayPeriodCalculator()),
    WEEK(new WeekPeriodCalculator()),
    MONTH(new MonthPeriodCalculator()),
    YEAR(new YearPeriodCalculator());
    
    private PeriodInstanceCalculator calculator;
    
    TimePeriod2(PeriodInstanceCalculator calc) {
        this.calculator = calc;
    }

    public long getPeriodInstanceFromTimeInMillis(long milliseconds) {
        return calculator.getPeriodInstanceFromTimeInMillis(milliseconds);
    }
    
    public static class DayPeriodCalculator implements PeriodInstanceCalculator {
        @Override
        public int getPeriodInstanceFromTimeInMillis(long milliseconds) {
            // TODO calculate the number of days since the epoch
            return 0;
        }
    }
    public static class WeekPeriodCalculator implements PeriodInstanceCalculator {
        @Override
        public int getPeriodInstanceFromTimeInMillis(long milliseconds) {
            // TODO calculate the number of weeks since the epoch
            return 0;
        }
    }
    public static class MonthPeriodCalculator implements PeriodInstanceCalculator {
        @Override
        public int getPeriodInstanceFromTimeInMillis(long milliseconds) {
            // TODO calculate the number of months since the epoch
            return 0;
        }
    }
    public static class YearPeriodCalculator implements PeriodInstanceCalculator {
        @Override
        public int getPeriodInstanceFromTimeInMillis(long milliseconds) {
            // TODO calculate the number of years since the epoch
            return 0;
        }
    }
}
