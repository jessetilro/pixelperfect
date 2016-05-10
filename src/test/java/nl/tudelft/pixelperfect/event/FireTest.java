package nl.tudelft.pixelperfect.event;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woute on 5/10/2016.
 */
public class FireTest  {

    private Fire toTest;

    /**
     * Setting up the Fire class for the test.
     */
    @Before
    public void initialize() {
        toTest = new Fire(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
    }

    /**
     * Testing the getId method.
     */
    @Test
    public void testGetId() {
        assertEquals(toTest.getId(), 1);
    }

    /**
     * Testing the getSummary method.
     */
    @Test
    public void testGetSummary() {
        assertEquals(toTest.getSummary(), "TestEvent");
    }

    /**
     * Testing the getDescription method.
     */
    @Test
    public void testGetDescription() {
        assertEquals(toTest.getDescription(), "An Event to test the Class.");
    }

    /**
     * When the duration of an event has not yet passed since it's creation, it should not be
     * recognized as expired.
     */
    @Test
    public void testIsExpiredFalse() {
        assertFalse(toTest.isExpired(83));
    }

    /**
     * When the duration of an event has passed since it's creation, it should be recognized as
     * expired.
     */
    @Test
    public void testIsExpiredTrue() {
        assertTrue(toTest.isExpired(85));
    }

    /**
     * Testing the getDamage method.
     */
    @Test
    public void testGetDamage() {
        assertEquals(toTest.getDamage(), 99.42, 0);
    }

}