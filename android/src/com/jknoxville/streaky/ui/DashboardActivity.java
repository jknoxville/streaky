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
import com.jknoxville.streaky.R;
import com.jknoxville.streaky.lib.Person;
import com.jknoxville.streaky.lib.UserAction;

public class DashboardActivity extends Activity {

    private Person self;
    private LinearLayout userActionListView;
    private OnClickListener userActionViewOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        this.self = Person.getInstance();
        this.userActionListView = (LinearLayout) findViewById(R.id.user_action_list);
        refreshActionViews();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        refreshActionViews();
    }
    

    private void refreshActionViews() {
        // Invalidate all UserActionViews so they refresh
        this.userActionListView.removeAllViews();
        
        for(UserAction action: self.getActions().values()) {
            ActionView view = new ActionView(this, action);
            view.setOnClickListener(getOnClickListener());
            userActionListView.addView(view);
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

    public void onClickAction(ActionView actionView) {
        Intent intent = new Intent(this, UserActionActivity.class);
        try {
            intent.putExtra(Constants.USER_ACTION_ID_KEY, actionView.getUserAction().getID());
            startActivity(intent);
        } catch (Exception e) {
            // Do nothing - theres nowhere to go.
            // TODO: log this
        }
    }

    private OnClickListener getOnClickListener() {
        if(userActionViewOnClickListener == null) {
            userActionViewOnClickListener = new OnClickListener() {

                @Override
                public void onClick(View v) {
                    onClickAction((ActionView) v);
                }
            };
        }
        return userActionViewOnClickListener;
    }

}
