package pixelperfect.event;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the Event class.
 * 
 * @author David Alderliesten
 *
 */
public class EventTest {
  private Event toTest;

  /**
   * Setting up the Event class for the test.
   */
  @Before
  public void initialize() {
    toTest = new Event(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
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
   * Testing the getDuration method.
   */
  @Test
  public void testGetDuration() {
    assertEquals(toTest.getDuration(), 42, 0);
  }

  /**
   * Testing the getTimeStamp method.
   */
  @Test
  public void testGetTimeStamp() {
    assertEquals(toTest.getTimeStamp(), 42, 0);
  }

  /**
   * Testing the getDamage method.
   */
  @Test
  public void testGetDamage() {
    assertEquals(toTest.getDamage(), 99.42, 0);
  }
}
