package nl.tudelft.pixelperfect.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import nl.tudelft.pixelperfect.Spaceship;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the Event class.
 * 
 * @author David Alderliesten
 *
 */
public abstract class EventTest {

  private Event toTest;

  /**
   * Setting up the Event class for the test.
   */
  @Before
  public void initialize() {
    toTest = createEvent();
  }

  /**
   * Factory method for testing.
   * @return class to be tested.
   */
  public abstract Event createEvent();

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
   * Testing damage done to the spaceship.
   */
  @Test
  public void testApplyDamage() {
    Spaceship toUse = new Spaceship();
    assertEquals(100.0, toUse.getHealth(), 0.0);
    
    toTest.applyDamage(toUse);
    assertEquals(100 - toTest.getDamage(), toUse.getHealth(), 0.0);
  }

  /**
   * Testing the getDamage method.
   */
  @Test
  public void testGetDamage() {
    assertEquals(99.42, toTest.getDamage(), 0.0);
  }
}
