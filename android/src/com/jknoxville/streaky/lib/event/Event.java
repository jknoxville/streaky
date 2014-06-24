package com.jknoxville.streaky.lib.event;

import java.util.Calendar;

public class Event {
    private final Calendar calendar;

    public Event(Calendar cal) {
        calendar = cal;
    }

    Calendar getCalendar() {
        return calendar;
    }
}
