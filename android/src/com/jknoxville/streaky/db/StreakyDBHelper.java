package com.jknoxville.streaky.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StreakyDBHelper extends SQLiteOpenHelper {
    
    public StreakyDBHelper(Context context) {
        super(context, StreakyContract.DATABASE_NAME, null, StreakyContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StreakyContract.SQL_CREATE_ACTIONS);
        db.execSQL(StreakyContract.SQL_CREATE_EVENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Currently on DATABASE_VERSION 1 so no upgrades are possible.
    }

}
