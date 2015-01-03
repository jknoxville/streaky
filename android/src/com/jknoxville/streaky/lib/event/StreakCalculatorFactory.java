package com.jknoxville.streaky.lib.event;


public class StreakCalculatorFactory {
    
    public enum Freq {
        DAY(new Day(), "Daily"),
        WEEK(new Week(), "Weekly");
        
        private TimePeriod period;
        private String friendlyName;
        
        Freq(TimePeriod frequency, String friendlyName) {
            this.period = frequency;
            this.friendlyName = friendlyName;
        }
        
        public TimePeriod getFrequency() {
            return period;
        }
        @Override
        public String toString() {
            return friendlyName;
        }
    }
    
    public static LengthStreakCalculator getLengthStreakCalculator(Freq freq) {
        return new LengthStreakCalculator(freq.getFrequency());
    }

}
