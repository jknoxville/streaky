package com.jknoxville.streaky.lib.event.time;

import java.util.LinkedList;
import java.util.List;

import com.jknoxville.streaky.lib.event.time.Moment;
import com.jknoxville.streaky.lib.event.time.TimePeriod2;
import com.jknoxville.streaky.lib.event.time.UnixTimeMoment;

import android.test.AndroidTestCase;

public class MomentTest extends AndroidTestCase {
    
    List<MomentTestCase> tests;
    List<TimePeriod2> periods;
    
    public void setUp() {
        tests = new LinkedList<MomentTestCase>();
        periods = new LinkedList<TimePeriod2>();
    }
    
    public void testGetAbsoluteTimeInPeriod() {
        for(MomentTestCase test: tests) {
            Moment moment = test.getMoment();
            for (TimePeriod2 period: periods) {
                long expectedAbsoluteTimeInPeriod = period.getPeriodInstanceFromTimeInMillis(test.getUnixTime());
                assertEquals(expectedAbsoluteTimeInPeriod, moment.getAbsoluteTimeInPeriod(period));
            }
        }
    }
    
    public void testGetNextAndPreviousMoments() {
        for (MomentTestCase test: tests) {
            Moment moment = test.getMoment();
            for (TimePeriod2 period: periods) {
                Moment nextMoment = moment.getNextMoment(period);
                Moment nextThenPreviousMoment = nextMoment.getPreviousMoment(period);
                Moment previousMoment = moment.getPreviousMoment(period);
                Moment previousThenNextMoment = previousMoment.getNextMoment(period);
                assertEquals(moment, nextThenPreviousMoment);
                assertEquals(moment, previousThenNextMoment);
                assertEquals(moment.getAbsoluteTimeInPeriod(period)+1, nextMoment.getAbsoluteTimeInPeriod(period));
                assertEquals(moment.getAbsoluteTimeInPeriod(period)-1, previousMoment.getAbsoluteTimeInPeriod(period));
            }
            
        }
    }
    
    public class MomentTestCase {
        private Moment moment;
        private long unixTime;
        public MomentTestCase(long unixTime) throws Exception {
            this.moment = new UnixTimeMoment(unixTime);
        }
        public Moment getMoment() {
            return moment;
        }
        public long getUnixTime() {
            return unixTime;
        }

    }

}
