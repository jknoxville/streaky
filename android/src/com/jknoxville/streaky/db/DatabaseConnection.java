package com.jknoxville.streaky.db;

import com.jknoxville.streaky.lib.UserAction;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
    
    public UserAction readActions() {
        // TODO
        return null;
    }

}
