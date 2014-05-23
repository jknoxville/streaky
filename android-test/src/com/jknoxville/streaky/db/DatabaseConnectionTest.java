package com.jknoxville.streaky.db;

import java.util.Calendar;
import java.util.List;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.SparseArray;

import com.jknoxville.streaky.lib.UserAction;
import com.jknoxville.streaky.lib.event.DayFrequency;
import com.jknoxville.streaky.lib.event.LengthStreakCalculator;

public class DatabaseConnectionTest extends AndroidTestCase {
    
    private static final String TEST_FILE_PREFIX = "test_";
    
    private DatabaseConnection db;
    private UserAction testAction;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), TEST_FILE_PREFIX);
        db = DatabaseConnection.getInstance(context);
        
        testAction = new UserAction("TestAction", new LengthStreakCalculator(new DayFrequency()), 0, null);
    }
    
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        
        DatabaseConnection.close();
        db = null;
        
        testAction = null;
    }
    
    public void testPreconditions() {
        assertNotNull(db);
        assertNotNull(testAction);
    }
    
    public void testWriteThenReadAction() {
        UserAction writtenAction = testAction;
        Calendar now = Calendar.getInstance();
        db.writeAction(writtenAction);
        db.writeEvent(writtenAction, now);
        SparseArray<UserAction> actions = db.readActions();
        UserAction readAction = actions.get(0);
        Calendar writtenCal = writtenAction.getCreationDate();
        Calendar readCal = readAction.getCreationDate();
        
        assertEquals(writtenAction.getID(), readAction.getID());
        assertEquals(writtenAction.getName(), readAction.getName());
        assertEquals(writtenAction.getStreakPeriod(), readAction.getStreakPeriod());
        assertEquals(writtenAction.getBestStreak().amount, readAction.getBestStreak().amount);
        assertEquals(writtenCal.get(Calendar.YEAR), readCal.get(Calendar.YEAR));
        assertEquals(writtenCal.get(Calendar.DAY_OF_YEAR), readCal.get(Calendar.DAY_OF_YEAR));
        assertEquals(writtenCal.getTimeInMillis(), readCal.getTimeInMillis());
        assertEquals(writtenAction.getCurrentStreak().amount, readAction.getCurrentStreak().amount);
        // Not yet implemented
        // assertEquals(writtenAction.getPreviousStreak().amount, readAction.getPreviousStreak().amount);
        assertEquals(writtenAction.getStreakType(), readAction.getStreakType());
        assertEquals(writtenAction.getStreakUnit(), readAction.getStreakUnit());
    }
    
    public void testWriteThenReadEvent() {
        UserAction writtenAction = testAction;
        Calendar now = Calendar.getInstance();
        db.writeAction(writtenAction);
        testAction.addEvent(now);
        db.writeEvent(writtenAction, now);
        List<UserAction> actions = db.getUserActions();
        UserAction readAction = actions.get(0);

        assertTrue(readAction.getEventLog().containsEventInDay(now.get(Calendar.YEAR), now.get(Calendar.WEEK_OF_YEAR), now.get(Calendar.DAY_OF_WEEK)));
    }
    
    public void testDeleteActivity() {
        UserAction writtenAction = testAction;
        Calendar now = Calendar.getInstance();
        db.writeAction(writtenAction);
        testAction.addEvent(now);
        db.writeEvent(writtenAction, now);
        UserAction readAction1 = db.getUserActions().get(0);
        
        db.deleteAction(readAction1);
        List<UserAction> actions = db.getUserActions();
        assertEquals(0, actions.size());
    }
    
}
