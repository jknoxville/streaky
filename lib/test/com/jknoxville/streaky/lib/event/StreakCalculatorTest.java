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
       
        tests.add(new CalcTestCase("0").setCurrentStreak(0));
        tests.add(new CalcTestCase("1").setCurrentStreak(1));
        tests.add(new CalcTestCase("01").setCurrentStreak(1));
        tests.add(new CalcTestCase("00").setCurrentStreak(0));
        tests.add(new CalcTestCase("10").setCurrentStreak(1));
        tests.add(new CalcTestCase("11").setCurrentStreak(2));
        tests.add(new CalcTestCase("101001101001").setCurrentStreak(1));
        tests.add(new CalcTestCase("00000111110").setCurrentStreak(5));
        tests.add(new CalcTestCase("0110100100111100").setCurrentStreak(0));
        tests.add(new CalcTestCase("100010110110110111110110101111").setCurrentStreak(4));
    }
    
    @Before
    public void setUp() {
        //TODO
    }

    @Test
    public void testGetCurrentStreak() {
        for(CalcTestCase testCase: tests) {
            Streak streak = calculator.getCurrentStreak(testCase.log);
            assertEquals("Testcase: "+testCase.logString, StreakUnit.DAY, streak.unit);
            assertEquals("Testcase: "+testCase.logString, testCase.currentStreak, streak.amount);
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
        public String logString;
        public CalcTestCase(String logString) throws Exception {
            EventLog log = new MockEventLog(logString);
            this.log = log;
            this.logString = logString;
        }
        public CalcTestCase setCurrentStreak(int expectedValue) {
            this.currentStreak = expectedValue;
            return this;
        }
    }

}
