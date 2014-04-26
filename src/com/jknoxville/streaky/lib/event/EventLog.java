package com.jknoxville.streaky.lib.event;

import java.util.LinkedList;

public class EventLog {
	
	LinkedList<Event> events;
	
	public EventLog() {
		events = new LinkedList<Event>();
	}

}
