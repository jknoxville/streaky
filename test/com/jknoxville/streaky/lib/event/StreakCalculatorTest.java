package com.jknoxville.streaky.lib.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jknoxville.streaky.lib.Streak;

@RunWith(value = Parameterized.class)
public class StreakCalculatorTest {
    
    @Parameters
    public static List<Object[]> data() {
        Object[][] data = new Object[][] {{new LengthStreakCalculator(new DayFrequency())}};
        return Arrays.asList(data);
    }
    
    private final StreakCalculator calculator;
    private final List<CalcTestCase> tests;
    
    public StreakCalculatorTest(StreakCalculator calc) throws Exception {
        this.calculator = calc;
        tests = new LinkedList<CalcTestCase>();
        EventLog log1 = new MockEventLog("100010110110110111110110101111");
        CalcTestCase test1 = new CalcTestCase(log1);
        test1.setCurrentStreak(4);
        tests.add(test1);
    }
    
    @Before
    public void setUp() {
        //TODO
    }

    @Test
    public void testGetCurrentStreak() {
        for(CalcTestCase testCase: tests) {
            Streak streak = calculator.getCurrentStreak(testCase.log);
            assertEquals(StreakUnit.DAY, streak.unit);
            assertEquals(testCase.currentStreak, streak.amount);
        }
        
    }

    @Ignore("Not yet ready")
    @Test
    public void testGetBestStreak() {
        fail("Not yet implemented");
    }

    //@Test
    public void testGetPreviousStreak() {
        fail("Not yet implemented");
    }
    
    public class CalcTestCase {
        public EventLog log;
        public int currentStreak;
        public CalcTestCase(EventLog log) {
            this.log = log;
        }
        public void setCurrentStreak(int expectedValue) {
            this.currentStreak = expectedValue;
        }
    }

}
