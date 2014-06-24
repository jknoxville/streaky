package com.jknoxville.streaky.lib.event;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.jknoxville.streaky.lib.Streak;

public class StreakCalculatorTest extends TestCase {
    
    private StreakCalculator calculator;
    private List<CalcTestCase> tests;
    
    public void setUp() throws Exception {
        this.calculator = new LengthStreakCalculator(new DayFrequency());
        tests = new LinkedList<CalcTestCase>();
       
        tests.add(new CalcTestCase("0").setCurrentStreak(0).setBestStreak(0));
        tests.add(new CalcTestCase("1").setCurrentStreak(1).setBestStreak(1));
        tests.add(new CalcTestCase("01").setCurrentStreak(1).setBestStreak(1));
        tests.add(new CalcTestCase("00").setCurrentStreak(0).setBestStreak(0));
        tests.add(new CalcTestCase("10").setCurrentStreak(1).setBestStreak(1));
        tests.add(new CalcTestCase("11").setCurrentStreak(2).setBestStreak(2));
        tests.add(new CalcTestCase("101001101001").setCurrentStreak(1).setBestStreak(2));
        tests.add(new CalcTestCase("00000111110").setCurrentStreak(5).setBestStreak(5));
        tests.add(new CalcTestCase("0110100100111100").setCurrentStreak(0).setBestStreak(4));
        tests.add(new CalcTestCase("100010110110110111110110101111").setCurrentStreak(4).setBestStreak(5));
    }

    public void testGetCurrentStreak() {
        for(CalcTestCase testCase: tests) {
            Streak streak = calculator.getCurrentStreak(testCase.log);
            Assert.assertSame("Testcase: "+testCase.logString, StreakUnit.DAY, streak.unit);
            Assert.assertSame("Testcase: "+testCase.logString, testCase.currentStreak, streak.amount);
        }
    }

    public void testGetBestStreak() {
        for(CalcTestCase testCase: tests) {
            Streak streak = calculator.getBestStreak(testCase.log);
            Assert.assertEquals("Testcase: "+testCase.logString, StreakUnit.DAY, streak.unit);
            Assert.assertEquals("Testcase: "+testCase.logString, testCase.bestStreak, streak.amount);
        }
    }

    // TODO testGetPreviousStreak()
    
    public class CalcTestCase {
        public EventLog log;
        public int currentStreak;
        public int bestStreak;
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
        public CalcTestCase setBestStreak(int expectedValue) {
            this.bestStreak = expectedValue;
            return this;
        }
    }

}
