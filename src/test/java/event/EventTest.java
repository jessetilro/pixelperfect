package event;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import pixelperfect.event.Event;

/**
 * Class for testing the Event class.
 * 
 * @author David Alderliesten
 *
 */
public class EventTest {
  Event toTest;

  /**
   * Setting up the Event class for the test.
   */
  @Before
  public void initialize() {
    toTest = new Event(1, "TestEvent", "An Event to test the Class.", 424.24, 99.42);
  }

  /**
   * Testing the getType method.
   */
  @Test
  public void testGetType() {
    assertEquals(toTest.getType(), 1);
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
   * Testing the getTimeStamp method.
   */
  @Test
  public void testGetTimeStamp() {
    assertEquals(toTest.getTimeStamp(), 424.24, 0);
  }

  /**
   * Testing the getDamage method.
   */
  @Test
  public void testGetDamage() {
    assertEquals(toTest.getDamage(), 99.42, 0);
  }
}
