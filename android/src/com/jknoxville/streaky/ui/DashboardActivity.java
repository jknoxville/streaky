package com.jknoxville.streaky.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.jknoxville.streaky.NewActivity;
import com.jknoxville.streaky.R;
import com.jknoxville.streaky.lib.MockPerson;
import com.jknoxville.streaky.lib.Person;
import com.jknoxville.streaky.lib.UserAction;

public class DashboardActivity extends Activity {
    
    private Person self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        self = new MockPerson();
        addActionViews();
    }
    
    private void addActionViews() {
        LinearLayout userActionList = (LinearLayout) findViewById(R.id.user_action_list);
        for(UserAction action: self.getActions()) {
            UserActionView view = new UserActionView(this, action);
            userActionList.addView(view);
            userActionList.invalidate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }
    
    @Override
    // Handle presses on the action bar items
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new:
                onClickNew();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void onClickNew() {
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }

}
