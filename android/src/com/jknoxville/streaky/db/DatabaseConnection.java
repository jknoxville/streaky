package com.jknoxville.streaky.db;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jknoxville.streaky.db.StreakyContract.Action;
import com.jknoxville.streaky.lib.UserAction;
import com.jknoxville.streaky.lib.event.StreakCalculator;
import com.jknoxville.streaky.lib.event.StreakCalculatorFactory;
import com.jknoxville.streaky.lib.event.StreakCalculatorFactory.Freq;

public class DatabaseConnection {

    private static DatabaseConnection instance;

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

    public List<UserAction> readActions() {
        List<UserAction> actions = new LinkedList<UserAction>();
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
//            Long period = c.getLong(c.getColumnIndexOrThrow(Action.COLUMN_NAME_PERIOD));
//            String unit = c.getString(c.getColumnIndexOrThrow(Action.COLUMN_NAME_PERIOD_UNIT));
            // TODO check that period == 1 and unit == "DAY" when more types are introduced
            StreakCalculator calc = StreakCalculatorFactory.getLengthStreakCalculator(Freq.DAY);
            String name = c.getString(c.getColumnIndexOrThrow(Action.COLUMN_NAME_ACTION_NAME));
            Long id = c.getLong(c.getColumnIndexOrThrow(Action._ID));
            UserAction action = new UserAction(name, calc, id.intValue());
            actions.add(action);
            c.moveToNext();
        }
        System.out.println("Actions: "+actions.size());
        return actions;
    }

}
