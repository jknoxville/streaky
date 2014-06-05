package com.jknoxville.streaky.lib;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.jknoxville.streaky.error.NameAlreadyExistsException;
import com.jknoxville.streaky.lib.event.StreakCalculator;
import com.jknoxville.streaky.ui.ActivityIcon;

public class Person {
    
    private static Person instance;
    Map<Integer, UserAction> actions;
    
    public static Person getInstance() {
        if(instance == null) {
            instance = new Person();
        }
        return instance;
    }
    
    protected Person() {
        actions = new HashMap<Integer, UserAction>();
    }
    
    public Collection<UserAction> getActions() {
        return actions.values();
    }
    
    public UserAction getAction(int actionID) {
        return actions.get(actionID);
    }
    
    public synchronized UserAction newUserAction(String name, StreakCalculator calc, ActivityIcon icon) throws NameAlreadyExistsException {
        validateName(name);
        int id = getNextUserActionID();
        UserAction action = new UserAction(name, calc, id, null, icon);
        addUserAction(action);
        return action;
    }
    
    public void addUserAction(UserAction action) {
        actions.put(action.getID(), action);
    }
    
    public void removeUserAction(UserAction action) {
        //TODO Remove from DB or at least mark it as removed in DB
        actions.remove(action.getID());
    }
    
    private void validateName(String name) throws NameAlreadyExistsException {
        for(UserAction action: actions.values()) {
            if(name.contentEquals(action.getName())) {
                throw new NameAlreadyExistsException();
            }
        }
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
    
    public void initialise(Collection<UserAction> actions) {
        if(this.actions.size() == 0) {
            for(UserAction action: actions) {
                addUserAction(action);
            }
        }
    }

}
