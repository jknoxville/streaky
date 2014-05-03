package com.jknoxville.streaky.lib;

import com.jknoxville.streaky.lib.event.StreakCalculatorFactory;

public class MockPerson extends Person {
    
    public MockPerson() {
        super();
        this.addUserAction(new UserAction("testAction", StreakCalculatorFactory.getLengthStreakCalculator(StreakCalculatorFactory.Freq.DAY)));
    }

}
