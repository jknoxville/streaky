package com.jknoxville.streaky.lib;

import java.util.ArrayList;
import java.util.List;

import com.jknoxville.streaky.lib.event.StreakCalculator;

public class Person {
    
    private static Person instance;
    List<UserAction> actions;
    
    public static Person getInstance() {
        if(instance == null) {
            instance = new Person();
        }
        return instance;
    }
    
    protected Person() {
        actions = new ArrayList<UserAction>();
    }
    
    public List<UserAction> getActions() {
        return actions;
    }
    
    public synchronized UserAction newUserAction(String name, StreakCalculator calc) {
        int id = getNextUserActionID();
        UserAction action = new UserAction(name, calc, id);
        addUserAction(action);
        return action;
    }
    
    public void addUserAction(UserAction action) {
        actions.add(action);
    }
    
    public void removeUserAction(UserAction action) {
        //TODO Remove from DB or at least mark it as removed in DB
        actions.remove(action);
    }
    
    // Gets the first unused id in the action list
    private int getNextUserActionID() {
        for(int id = 0; id < actions.size(); id++) {
            if(actions.get(id) == null) {
                return id;
            }
        }
        return actions.size();
    }

}
