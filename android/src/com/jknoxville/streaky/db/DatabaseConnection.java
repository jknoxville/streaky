package com.jknoxville.streaky.db;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.SparseArray;

import com.jknoxville.streaky.db.StreakyContract.Action;
import com.jknoxville.streaky.db.StreakyContract.Event;
import com.jknoxville.streaky.lib.UserAction;
import com.jknoxville.streaky.lib.event.StreakCalculator;
import com.jknoxville.streaky.lib.event.StreakCalculatorFactory;
import com.jknoxville.streaky.lib.event.StreakCalculatorFactory.Freq;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private static final String TAG = "DatabaseConnection";

    private SQLiteDatabase db;

    private DatabaseConnection(SQLiteDatabase db) {
        this.db = db;
    }

    // Public interface exposing the static instance of DatabaseConnection
    public static DatabaseConnection getInstance(Context context) {
        if(instance == null) {
            StreakyDBHelper dbHelper = new StreakyDBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            instance = new DatabaseConnection(db);
        }
        return instance;
    }

    public boolean writeAction(UserAction action) {
        ContentValues values = new ContentValues();
        values.put(Action._ID, action.getID());
        values.put(Action.COLUMN_NAME_CREATION_DATE, action.getCreationDate().getTimeInMillis());
        values.put(Action.COLUMN_NAME_ACTION_NAME, action.getName());
        values.put(Action.COLUMN_NAME_CALCULATOR_TYPE, action.getStreakType().toString());
        values.put(Action.COLUMN_NAME_PERIOD, action.getStreakPeriod());
        values.put(Action.COLUMN_NAME_PERIOD_UNIT, action.getStreakUnit().name());
        Long id = db.insert(Action.TABLE_NAME, null, values);
        return id >= 0;
    }
    
    public boolean writeEvent(UserAction action, Calendar cal) {
        ContentValues values = new ContentValues();
        values.put(Event.COLUMN_NAME_USER_ACTION, action.getID());
        values.put(Event.COLUMN_NAME_EVENT_TIME, cal.getTimeInMillis());
        Long id = db.insert(Event.TABLE_NAME, null, values);
        return id >= 0;
    }

    public List<UserAction> getUserActions() {
        SparseArray<UserAction> actions = readActions();
        loadEvents(actions);
        List<UserAction> actionList = new LinkedList<UserAction>();
        for(int i=0; i<actions.size(); i++) {
            actionList.add(actions.valueAt(i));
        }
        return actionList;

    }

    SparseArray<UserAction> readActions() {
        SparseArray<UserAction> actions = new SparseArray<UserAction>();
        String[] projection = {
                Action._ID,
                Action.COLUMN_NAME_ACTION_NAME,
                Action.COLUMN_NAME_CALCULATOR_TYPE,
                Action.COLUMN_NAME_CREATION_DATE,
                Action.COLUMN_NAME_IS_DELETED,
                Action.COLUMN_NAME_PERIOD,
                Action.COLUMN_NAME_PERIOD_UNIT
        };
        Cursor c = db.query(Action.TABLE_NAME, projection, null, null, null, null, null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            // Long period = c.getLong(c.getColumnIndexOrThrow(Action.COLUMN_NAME_PERIOD));
            // String unit = c.getString(c.getColumnIndexOrThrow(Action.COLUMN_NAME_PERIOD_UNIT));
            // TODO check that period == 1 and unit == "DAY" when more types are introduced
            StreakCalculator calc = StreakCalculatorFactory.getLengthStreakCalculator(Freq.DAY);
            String name = c.getString(c.getColumnIndexOrThrow(Action.COLUMN_NAME_ACTION_NAME));
            Long creationTime = c.getLong(c.getColumnIndexOrThrow(Action.COLUMN_NAME_CREATION_DATE));
            Calendar creationDate = Calendar.getInstance();
            creationDate.setTimeInMillis(creationTime);
            int id = Long.valueOf(c.getLong(c.getColumnIndexOrThrow(Action._ID))).intValue();
            UserAction action = new UserAction(name, calc, id, creationDate);
            actions.put(id, action);
            c.moveToNext();
            Log.d(TAG, "Loaded action "+name);
        }
        Log.d(TAG, "Loaded "+actions.size()+" actions from DB.");
        return actions;
    }

    private void loadEvents(SparseArray<UserAction> actions) {
        String[] projection = {
                Event.COLUMN_NAME_EVENT_TIME,
                Event.COLUMN_NAME_USER_ACTION
        };
        Cursor c = db.query(Event.TABLE_NAME, projection, null, null, null, null, null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            UserAction action = actions.get((int) c.getLong(c.getColumnIndexOrThrow(Event.COLUMN_NAME_USER_ACTION)));
            Long milliseconds = c.getLong(c.getColumnIndexOrThrow(Event.COLUMN_NAME_EVENT_TIME));
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(milliseconds);
            action.addEvent(cal);
            c.moveToNext();
            Log.d(TAG, "Loaded event at: "+cal.toString());
        }
        
        Log.d(TAG, "Loaded "+c.getCount()+" events from DB.");
    }
    
    public static void close() {
        if(instance != null) {
            instance.db.close();
            instance = null;
            Log.d(TAG, "Database closed.");
        } else {
            Log.e(TAG, "Tried to close DB but it doesn't exist.");
        }
        
    }

}
