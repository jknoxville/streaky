package com.jknoxville.streaky.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jknoxville.streaky.Constants;
import com.jknoxville.streaky.R;
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
        this.action = Person.getInstance().getActions().get(userActionID);
        addViews();
    }
    
    private void addViews() {
        TextView tv = (TextView) findViewById(R.id.test_text);
        tv.setText(action.getName()+": "+action.getCurrentStreak().amount);
    }
    
    public void onCheckin(View view) {
        this.action.newEvent();
        invalidateView();
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
        }
        return super.onOptionsItemSelected(item);
    }

}