package com.jknoxville.streaky.db;

import java.util.Calendar;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.SparseArray;

import com.jknoxville.streaky.lib.UserAction;
import com.jknoxville.streaky.lib.event.DayFrequency;
import com.jknoxville.streaky.lib.event.LengthStreakCalculator;

public class DatabaseConnectionTest extends AndroidTestCase {
    
    private static final String TEST_FILE_PREFIX = "test_";
    
    private DatabaseConnection db;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), TEST_FILE_PREFIX);
        db = DatabaseConnection.getInstance(context);
    }
    
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        
        DatabaseConnection.close();
        db = null;
    }
    
    public void testPreconditions() {
        assertNotNull(db);
    }
    
    public void testWriteThenReadAction() {
        UserAction writtenAction = new UserAction("TestAction", new LengthStreakCalculator(new DayFrequency()), 0);
        db.writeAction(writtenAction);
        SparseArray<UserAction> actions = db.readActions();
        UserAction readAction = actions.get(0);
        assertEquals(writtenAction.getID(), readAction.getID());
        assertEquals(writtenAction.getName(), readAction.getName());
        assertEquals(writtenAction.getStreakPeriod(), readAction.getStreakPeriod());
        assertEquals(writtenAction.getBestStreak().amount, readAction.getBestStreak().amount);
        Calendar writtenCal = writtenAction.getCreationDate();
        Calendar readCal = readAction.getCreationDate();
        assertEquals(writtenCal.get(Calendar.YEAR), readCal.get(Calendar.YEAR));
        assertEquals(writtenCal.get(Calendar.DAY_OF_YEAR), readCal.get(Calendar.DAY_OF_YEAR));
        assertEquals(writtenAction.getCurrentStreak().amount, readAction.getCurrentStreak().amount);
        // Not yet implemented
        // assertEquals(writtenAction.getPreviousStreak().amount, readAction.getPreviousStreak().amount);
        assertEquals(writtenAction.getStreakType(), readAction.getStreakType());
        assertEquals(writtenAction.getStreakUnit(), readAction.getStreakUnit());
    }
    
}
