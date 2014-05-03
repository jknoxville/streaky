package com.jknoxville.streaky;

public abstract class Constants {
    private static final String appNamespace = "com.jknoxville.streaky";
    public static final String USER_ACTION_ID_KEY = prefix("user_action_id");
    
    private static String prefix(String str) {
        return appNamespace+"."+str;
    }
}
