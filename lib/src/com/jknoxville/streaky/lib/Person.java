package com.jknoxville.streaky.lib;

import java.util.ArrayList;
import java.util.List;

public class Person {
    
    public static final Person instance = new Person();
    List<UserAction> actions;
    
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
