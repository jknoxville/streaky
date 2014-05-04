package com.jknoxville.streaky.lib;

import java.util.ArrayList;
import java.util.List;

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
    
    public void addUserAction(UserAction action) {
        actions.add(action);
    }
    
    public void removeUserAction(UserAction action) {
        //TODO Remove from DB or at least mark it as removed in DB
        actions.remove(action);
    }

}
