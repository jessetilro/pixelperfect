package nl.tudelft.pixelperfect.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import nl.tudelft.pixelperfect.Spaceship;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the EventLog class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class EventLogTest extends EventListenerTest {
  EventLog eventLogToTest;
  Spaceship spaceshipToTest;

  /**
   * Setting up the class for the EventLogTest.
   */
  @Before
  public void initialise() {
    spaceshipToTest = new Spaceship();
    eventLogToTest = new EventLog(spaceshipToTest);
  }

  /**
   * Test the eventlist getter, and validate that it is empty on start.
   */
  @Test
  public void testGetEventsEmpty() {
    assertTrue(eventLogToTest.getEvents().isEmpty() == true);
  }

  /**
   * Test the spaceship getter, that it is equal to the given spaceship at the start.
   */
  @Test
  public void testGetSpaceship() {
    assertEquals(spaceshipToTest, eventLogToTest.getSpaceship());
  }
}
