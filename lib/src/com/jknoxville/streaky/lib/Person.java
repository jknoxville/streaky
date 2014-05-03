package com.jknoxville.streaky.lib;

import java.util.PriorityQueue;

import com.jknoxville.streaky.lib.UserAction.UserActionPriorityComparator;

public class Person {
    
    private static final int INITIAL_CAPACITY = 11;
    PriorityQueue<UserAction> actions;
    
    public Person() {
        actions = new PriorityQueue<UserAction>(INITIAL_CAPACITY, new UserActionPriorityComparator());
    }
    
    public PriorityQueue<UserAction> getActions() {
        return actions;
    }
    
    public void addUserAction(UserAction action) {
        actions.add(action);
    }
    
    public void removeUserAction(UserAction action) {
        //TODO Remove from DB or at least mark it as removed in DB
        actions.remove(action);
    }

}
