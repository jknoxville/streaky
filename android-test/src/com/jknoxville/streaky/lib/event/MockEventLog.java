package com.jknoxville.streaky.lib.event;

import java.util.Calendar;

public class MockEventLog extends EventLog {

    /**
     * EventLog Mock class
     * <p>
     * Allows for easy creation of test EventLog objects.
     * 
     * @param logString A string consisting of only 0s and 1s. Each character of the string represents one day in the log. The start date of the event log is set to the date of the first character, and the last character represents the current day.
     */
    public MockEventLog(String logString) throws Exception {
        super(Calendar.getInstance());
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
    
    public MockEventLog(Calendar startDate) {
        super(startDate);
    }
}
