package com.jknoxville.streaky.lib;

import com.jknoxville.streaky.lib.event.StreakCalculatorFactory;

public class MockPerson extends Person {
    
    public static int timesMocked = 0;
    
    // Private constructor not to be used
    private MockPerson() {
        super();
    }
    
    public static void addMockActivity(Person p) {
        p.addUserAction(new UserAction("testAction", StreakCalculatorFactory.getLengthStreakCalculator(StreakCalculatorFactory.Freq.DAY), 0, null));
        timesMocked++;
    }

}
