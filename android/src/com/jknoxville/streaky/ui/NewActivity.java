package com.jknoxville.streaky.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.jknoxville.streaky.R;
import com.jknoxville.streaky.db.DatabaseConnection;
import com.jknoxville.streaky.error.NameAlreadyExistsException;
import com.jknoxville.streaky.lib.Person;
import com.jknoxville.streaky.lib.UserAction;
import com.jknoxville.streaky.lib.event.StreakCalculator;
import com.jknoxville.streaky.lib.event.StreakCalculatorFactory;
import com.jknoxville.streaky.lib.event.StreakCalculatorFactory.Freq;

public class NewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        // Show the Up button in the action bar.
        setupActionBar();
        setupSpinner();
    }

    private void setupSpinner() {
        Spinner dropdown = (Spinner) findViewById(R.id.freq_spinner);
        ArrayAdapter<Freq> adapter = new ArrayAdapter<Freq>(this, android.R.layout.simple_spinner_item, Freq.values());
        dropdown.setAdapter(adapter);
    }

    private void setupActionBar() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onSaveActivity(View view) {
        if(hasRequiredInfo()) {
            try {
                UserAction action = Person.getInstance().newUserAction(readName(), getCalculator());
                DatabaseConnection.getInstance(this).writeAction(action);
                finish();
            } catch(NameAlreadyExistsException e) {
                promptForNonExistentName();
            }

        } else {
            promptForMissingInfo();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private String readName() {
        EditText nameView = (EditText) findViewById(R.id.new_activity_name);
        return nameView.getText().toString();
    }
    private StreakCalculator getCalculator() {
        Spinner targetSelector = (Spinner) findViewById(R.id.freq_spinner);
        Freq target = (Freq) targetSelector.getSelectedItem();
        return StreakCalculatorFactory.getLengthStreakCalculator(target);
    }
    private boolean hasRequiredInfo() {
        return !readName().isEmpty();
    }
    private void promptForMissingInfo() {
        // TODO: show hint or something next to missing fields
    }
    private void promptForNonExistentName() {
        // TODO: get user to try a different name
    }

}
