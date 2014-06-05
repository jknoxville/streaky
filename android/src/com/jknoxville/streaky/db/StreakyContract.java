package com.jknoxville.streaky.db;

import android.provider.BaseColumns;

public final class StreakyContract {
    
    // Private constructor. Do not use.
    private StreakyContract() {
    }
    
    // Increment this DATABASE_VERSION manually whenever the schema is changed.
    public static final int DATABASE_VERSION = 2;
    public static final String
        DATABASE_NAME = "streaky.db",
        SQL_CREATE_EVENTS = 
                "CREATE TABLE " + Event.TABLE_NAME + " (" +
                    Event._ID + " INTEGER PRIMARY KEY, " +
                    Event.COLUMN_NAME_USER_ACTION + " INTEGER NOT NULL, " +
                    Event.COLUMN_NAME_EVENT_TIME + " INTEGER NOT NULL, " +
                    "FOREIGN KEY (" + Event.COLUMN_NAME_USER_ACTION + ") REFERENCES " +
                        Action.TABLE_NAME + " (" + Action._ID + ") " +
                ");",
        SQL_CREATE_ACTIONS = 
                "CREATE TABLE " + Action.TABLE_NAME + " (" +
                    Action._ID + " INTEGER PRIMARY KEY, " +
                    Action.COLUMN_NAME_CREATION_DATE + " INTEGER NOT NULL, " +
                    Action.COLUMN_NAME_ACTION_NAME + " TEXT NOT NULL, " +
                    Action.COLUMN_NAME_PERIOD + " INTEGER NOT NULL, " +
                    Action.COLUMN_NAME_PERIOD_UNIT + " TEXT NOT NULL, " +
                    Action.COLUMN_NAME_CALCULATOR_TYPE + " TEXT NOT NULL, " +
                    Action.COLUMN_NAME_IS_DELETED + " INTEGER DEFAULT 0, " +
                    Action.COLUMN_NAME_ICON + " TEXT NOT NULL " +
                ");",
        SQL_DELETE_ACTION =
                "DELETE FROM "+Action.TABLE_NAME + " WHERE " + Action._ID + " = \"";
    
    public static abstract class Event implements BaseColumns {
        public static final String 
            TABLE_NAME = "event",
            COLUMN_NAME_USER_ACTION = "user_action",
            COLUMN_NAME_EVENT_TIME = "event_time";
    }
    
    public static abstract class Action implements BaseColumns {
        public static final String 
            TABLE_NAME = "action",
            COLUMN_NAME_CREATION_DATE = "creation_date",
            COLUMN_NAME_ACTION_NAME = "action_name",
            COLUMN_NAME_PERIOD = "time_period",
            COLUMN_NAME_PERIOD_UNIT = "time_period_unit",
            COLUMN_NAME_CALCULATOR_TYPE = "calculator_type",
            COLUMN_NAME_IS_DELETED = "is_deleted",
            COLUMN_NAME_ICON = "icon";
    }
    
    public static String getDeleteActionSQL(String actionID) {
        return SQL_DELETE_ACTION + actionID + "\";";
    }

}
