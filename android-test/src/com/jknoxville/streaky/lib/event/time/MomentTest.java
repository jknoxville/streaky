package com.jknoxville.streaky.lib.event.time;

import android.test.AndroidTestCase;

public class MomentTest extends AndroidTestCase {
    
//    private static final long oneDayInMillis = 1000*60*60*24;
//    
//    private final TimePeriod2[] periods = TimePeriod2.values();
//    
//    private List<MomentTestCase> earlyTests;
//    private List<MomentTestCase> lateTests;
//    private List<MomentTestCase> typicalTests;
//    private List<List<MomentTestCase>> allTests;
//    
//    long[] tooEarlyTestTimes = {
//            0, 1, 23, oneDayInMillis, oneDayInMillis + 500
//            };
//    
//    long[] typicalTestTimes = {
//            2*oneDayInMillis, 2*oneDayInMillis + 500, 345*oneDayInMillis
//            };
//    
//    long[] tooLateTestTimes = {
//            Long.MAX_VALUE - oneDayInMillis, Long.MAX_VALUE - 500 - oneDayInMillis
//            };
//    
//    public void setUp() {
//        earlyTests = new LinkedList<MomentTestCase>();
//        for (long time: tooEarlyTestTimes) {
//            earlyTests.add(new MomentTestCase(time));
//        }
//        
//        lateTests = new LinkedList<MomentTestCase>();
//        for (long time: tooLateTestTimes) {
//            lateTests.add(new MomentTestCase(time));
//        }
//        
//        typicalTests = new LinkedList<MomentTestCase>();
//        for (long time: typicalTestTimes) {
//            typicalTests.add(new MomentTestCase(time));
//        }
//        
//        allTests = new LinkedList<List<MomentTestCase>>();
//        allTests.add(earlyTests);
//        allTests.add(lateTests);
//        allTests.add(typicalTests);
//        
//    }
//    
//    public void testGetAbsoluteTimeInPeriods() {
//        for (List<MomentTestCase> testList: allTests) {
//            getAbsoluteTimeInPeriodTestHelper(testList);
//        }
//    }
//    
//    public void getAbsoluteTimeInPeriodTestHelper(List<MomentTestCase> tests) {
//        for(MomentTestCase test: tests) {
//            Moment moment = test.getMoment();
//            for (TimePeriod2 period: periods) {
//                long expectedAbsoluteTimeInPeriod = period.getPeriodInstanceFromTimeInMillis(test.getUnixTime());
//                assertEquals(expectedAbsoluteTimeInPeriod, moment.getAbsoluteTimeInPeriod(period));
//            }
//        }
//    }
//    
//    public void testGetNextAndPreviousMomentsForEarlyTimes() {
//        try {
//            getNextAndPreviousMomentsTestHelper(earlyTests);
//            fail("No OutOfBoundsException thrown");
//        } catch (OutOfBoundsException e) {
//            // Test passes
//        }
//    }
//    
//    public void testGetNextAndPreviousMomentsForLateTimes() {
//        try {
//            getNextAndPreviousMomentsTestHelper(lateTests);
//            fail("No OutOfBoundsException thrown");
//        } catch (OutOfBoundsException e) {
//            // Test passes
//        }
//    }
//    
//    public void testGetNextAndPreviousMomentsForTypicalTimes() {
//        try {
//            getNextAndPreviousMomentsTestHelper(typicalTests);
//        } catch (Exception e) {
//            fail("Exception Raised");
//        }
//    }
//    
//    public void getNextAndPreviousMomentsTestHelper(List<MomentTestCase> tests) throws OutOfBoundsException {
//        for (MomentTestCase test: tests) {
//            Moment moment = test.getMoment();
//            for (TimePeriod2 period: periods) {
//                Moment nextMoment = moment.getNextMoment(period);
//                Moment nextThenPreviousMoment = nextMoment.getPreviousMoment(period);
//                Moment previousMoment = moment.getPreviousMoment(period);
//                Moment previousThenNextMoment = previousMoment.getNextMoment(period);
//                assertEquals(moment, nextThenPreviousMoment);
//                assertEquals(moment, previousThenNextMoment);
//                assertEquals(moment.getAbsoluteTimeInPeriod(period)+1, nextMoment.getAbsoluteTimeInPeriod(period));
//                assertEquals(moment.getAbsoluteTimeInPeriod(period)-1, previousMoment.getAbsoluteTimeInPeriod(period));
//            }
//        }
//    }
//    
//    public class MomentTestCase {
//        private Moment moment;
//        private long unixTime;
//        public MomentTestCase(long unixTime) {
//            this.moment = new UnixTimeMoment(unixTime);
//        }
//        public Moment getMoment() {
//            return moment;
//        }
//        public long getUnixTime() {
//            return unixTime;
//        }
//
//    }

}
