package com.jknoxville.streaky.lib.event;


public class StreakCalculatorFactory {
    
    public enum Freq {
        DAY(new DayFrequency(), "Daily");
        
        private Frequency frequency;
        private String friendlyName;
        
        Freq(Frequency frequency, String friendlyName) {
            this.frequency = frequency;
            this.friendlyName = friendlyName;
        }
        
        public Frequency getFrequency() {
            return frequency;
        }
        public String getFriendlyName() {
            return friendlyName;
        }
    }
    
    public static LengthStreakCalculator getLengthStreakCalculator(Freq freq) {
        return new LengthStreakCalculator(freq.getFrequency());
    }

}
