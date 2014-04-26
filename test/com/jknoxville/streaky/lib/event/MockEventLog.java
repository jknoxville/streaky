package com.jknoxville.streaky.lib.event;

import java.util.Calendar;

public class MockEventLog extends EventLog {
    public MockEventLog(String logString) throws Exception {
        this.startDate.add(Calendar.DAY_OF_YEAR, 1-logString.length());
        Calendar cursor = (Calendar) this.startDate.clone();
        for(int i=0; i<logString.length(); i++, cursor.add(Calendar.DAY_OF_YEAR, 1)) {
            char current = logString.charAt(i);
            if(current == '1') {
                this.addEvent(cursor);
            } else if(current != '0') {
                throw new Exception();
            }
        }
    }
}
