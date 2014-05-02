package com.jknoxville.streaky.lib.event;

import java.util.HashMap;

public class StreakCalculatorFactory {
    
    public enum Freq {
        DAY;
    }
    
    private static final HashMap<Freq, Frequency> frequencies = new HashMap<Freq, Frequency>();
    
    static {
        frequencies.put(Freq.DAY, new DayFrequency());
    }
    
    public static LengthStreakCalculator getLengthStreakCalculator(Freq freq) {
        return new LengthStreakCalculator(frequencies.get(freq));
    }

}
