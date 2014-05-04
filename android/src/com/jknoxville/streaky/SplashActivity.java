package com.jknoxville.streaky;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.jknoxville.streaky.db.DatabaseConnection;
import com.jknoxville.streaky.lib.MockPerson;
import com.jknoxville.streaky.lib.Person;
import com.jknoxville.streaky.ui.DashboardActivity;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DatabaseConnection db = DatabaseConnection.getInstance(this);
        Person self = Person.getInstance();
        
        db.readActions();
        
        // Placeholder until DB set up
        if(MockPerson.timesMocked == 0) {
            MockPerson.addMockActivity(Person.getInstance());
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
