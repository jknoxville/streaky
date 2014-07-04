package com.jknoxville.streaky.lib.event.time;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.MutableDateTime;
import org.joda.time.Weeks;
import org.joda.time.Years;

import com.jknoxville.streaky.error.OutOfBoundsException;

public enum TimePeriod2 {
    
    YEAR(new YearPeriodCalculator()),
    MONTH(new MonthPeriodCalculator()),
    WEEK(new WeekPeriodCalculator()),
    DAY(new DayPeriodCalculator());
    
    private PeriodInstanceCalculator calculator;
    private static final MutableDateTime epoch = new MutableDateTime();
    
    static {
        epoch.setDate(0);
    }
    
    TimePeriod2(PeriodInstanceCalculator calc) {
        this.calculator = calc;
    }

    public long getPeriodInstanceFromTimeInMillis(long milliseconds) {
        return calculator.getPeriodInstanceFromTimeInMillis(milliseconds);
    }
    
    public long addNPeriodsTo(int numPeriods, long milliseconds) throws OutOfBoundsException {
        return calculator.addNPeriodsToAndCheckBounds(numPeriods, milliseconds);
    }
    
    public static class DayPeriodCalculator extends PeriodInstanceCalculator {
        @Override
        public int getPeriodInstanceFromTimeInMillis(long milliseconds) {
            DateTime instanceTime = new DateTime();
            int days = Days.daysBetween(epoch, instanceTime).getDays();
            return days;
        }

        @Override
        public long addNPeriodsTo(int numPeriods, long milliseconds) {
            MutableDateTime time = new MutableDateTime();
            time.setDate(milliseconds);
            time.addDays(numPeriods);
            return time.getMillis();
        }
    }
    public static class WeekPeriodCalculator extends PeriodInstanceCalculator {
        @Override
        public int getPeriodInstanceFromTimeInMillis(long milliseconds) {
            DateTime instanceTime = new DateTime();
            int weeks = Weeks.weeksBetween(epoch, instanceTime).getWeeks();
            return weeks;
        }

        @Override
        public long addNPeriodsTo(int numPeriods, long milliseconds) {
            MutableDateTime time = new MutableDateTime();
            time.setDate(milliseconds);
            time.addWeeks(numPeriods);
            return time.getMillis();
        }
    }
    public static class MonthPeriodCalculator extends PeriodInstanceCalculator {
        @Override
        public int getPeriodInstanceFromTimeInMillis(long milliseconds) {
            DateTime instanceTime = new DateTime();
            int months = Months.monthsBetween(epoch, instanceTime).getMonths();
            return months;
        }

        @Override
        public long addNPeriodsTo(int numPeriods, long milliseconds) {
            MutableDateTime time = new MutableDateTime();
            time.setDate(milliseconds);
            time.addMonths(numPeriods);
            return time.getMillis();
        }
    }
    public static class YearPeriodCalculator extends PeriodInstanceCalculator {
        @Override
        public int getPeriodInstanceFromTimeInMillis(long milliseconds) {
            DateTime instanceTime = new DateTime();
            int years = Years.yearsBetween(epoch, instanceTime).getYears();
            return years;
        }

        @Override
        public long addNPeriodsTo(int numPeriods, long milliseconds) {
            MutableDateTime time = new MutableDateTime();
            time.setDate(milliseconds);
            time.addYears(numPeriods);
            return time.getMillis();
        }
    }
}
