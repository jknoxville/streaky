package com.jknoxville.streaky.ui;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jknoxville.streaky.Constants;
import com.jknoxville.streaky.R;
import com.jknoxville.streaky.db.DatabaseConnection;
import com.jknoxville.streaky.lib.Person;
import com.jknoxville.streaky.lib.UserAction;

public class UserActionActivity extends Activity {
    
    private UserAction action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_action);
        setupActionBar();
        int userActionID = getIntent().getIntExtra(Constants.USER_ACTION_ID_KEY, -1);
        if(userActionID < 0) {
            this.finish();
        }
        this.action = Person.getInstance().getAction(userActionID);
        addViews();
    }
    
    private void addViews() {
        TextView tv = (TextView) findViewById(R.id.test_text);
        tv.setText(action.getName()+": "+action.getCurrentStreak().amount);
        LinearLayout layout = (LinearLayout) findViewById(R.id.relative_layout);
        UserActionLogView logView = new UserActionLogView(this, action);
        layout.addView(logView, 1);
    }
    
    public void onCheckin(View view) {
        Calendar event = this.action.newEvent();
        DatabaseConnection.getInstance(this).writeEvent(action, event);
        invalidateView();
    }
    
    public void onRemoveActivity(View view) {
        Person.getInstance().removeUserAction(action);
        DatabaseConnection.getInstance(this).deleteAction(action);
        action = null;
        this.finish();
    }
    
    private void invalidateView() {
        // TODO
        TextView tv = (TextView) findViewById(R.id.test_text);
        tv.setText(action.getName()+": "+action.getCurrentStreak().amount);
        tv.invalidate();
    }

    private void setupActionBar() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_remove :
                this.onRemoveActivity(findViewById(R.id.action_remove));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
