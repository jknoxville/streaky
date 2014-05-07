package com.jknoxville.streaky.lib.event;


public class StreakCalculatorFactory {
    
    public enum Freq {
        DAY(new DayFrequency());
        
        public Frequency frequency;
        Freq(Frequency frequency) {
            this.frequency = frequency;
        }
        public Frequency getFrequency() {
            return frequency;
        }
    }
    
    public static LengthStreakCalculator getLengthStreakCalculator(Freq freq) {
        return new LengthStreakCalculator(freq.getFrequency());
    }

}
