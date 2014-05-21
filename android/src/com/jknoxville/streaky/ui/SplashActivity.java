package com.jknoxville.streaky.ui;

import java.util.Collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.jknoxville.streaky.R;
import com.jknoxville.streaky.db.DatabaseConnection;
import com.jknoxville.streaky.lib.Person;
import com.jknoxville.streaky.lib.UserAction;

public class SplashActivity extends Activity {
    
    private static final int DASHBOARD_RESULT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DatabaseConnection db = DatabaseConnection.getInstance(this);
        Person self = Person.getInstance();
        
        // TODO: Read from database in a new thread
        Collection<UserAction> actions = db.getUserActions();
        self.initialise(actions);
        
        onFinishedLoading();
    }
    
    private void onFinishedLoading() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivityForResult(intent, DASHBOARD_RESULT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        DatabaseConnection.close();
        this.finish();
    }
    
}
