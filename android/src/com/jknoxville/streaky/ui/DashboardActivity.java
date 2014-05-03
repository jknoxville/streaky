package com.jknoxville.streaky.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.jknoxville.streaky.Constants;
import com.jknoxville.streaky.NewActivity;
import com.jknoxville.streaky.R;
import com.jknoxville.streaky.UserActionActivity;
import com.jknoxville.streaky.lib.MockPerson;
import com.jknoxville.streaky.lib.Person;
import com.jknoxville.streaky.lib.UserAction;

public class DashboardActivity extends Activity {
    
    private Person self;
    private OnClickListener userActionViewOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        /* TODO:
         * Before you get to this activity, initialize the static Person instance
         * by reading from the DB / creating the DB. Maybe during a splash screen?
         * Then can do:
         * self = Person.instance;
         */
        self = new MockPerson();
        addActionViews();
    }
    
    private void addActionViews() {
        LinearLayout userActionList = (LinearLayout) findViewById(R.id.user_action_list);
        for(UserAction action: self.getActions()) {
            UserActionView view = new UserActionView(this, action);
            view.setOnClickListener(getOnClickListener());
            userActionList.addView(view);
        }
        userActionList.invalidate();
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
    
    public void onClickAction(UserActionView actionView) {
        Intent intent = new Intent(this, UserActionActivity.class);
        intent.putExtra(Constants.USER_ACTION_ID_KEY, actionView.getUserAction().getID());
        startActivity(intent);
    }
    
    private OnClickListener getOnClickListener() {
        if(userActionViewOnClickListener == null) {
            userActionViewOnClickListener = new OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    onClickAction((UserActionView) v);
                }
            };
        }
        return userActionViewOnClickListener;
    }

}
