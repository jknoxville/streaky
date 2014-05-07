package com.jknoxville.streaky.ui;

import java.util.Collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.jknoxville.streaky.R;
import com.jknoxville.streaky.db.DatabaseConnection;
import com.jknoxville.streaky.lib.MockPerson;
import com.jknoxville.streaky.lib.Person;
import com.jknoxville.streaky.lib.UserAction;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DatabaseConnection db = DatabaseConnection.getInstance(this);
        Person self = Person.getInstance();
        
        // TODO: Read from database in a new thread
        Collection<UserAction> actions = db.readActions();
        System.out.println("Number of actions: "+actions.size());
        for(UserAction action: actions) {
            self.addUserAction(action);
        }
        
        onFinishedLoading();
    }
    
    private void onFinishedLoading() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

}
