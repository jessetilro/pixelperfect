package nl.tudelft.pixelperfect.event.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.pixelperfect.Constants;
import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.event.EventReader;

/**
 * Test Suite for the EventFactory class.
 * 
 * @author Wouter Zirkzee
 * @author Jesse Tilro
 * 
 */
public abstract class EventFactoryTest {

  private EventFactory object;

  /**
   * Factory method for creating a test object.
   * 
   * @return A test object.
   */
  public abstract EventFactory createEventFactory();

  /**
   * Get the test object.
   * 
   * @return The test object.
   */
  public EventFactory getObject() {
    return object;
  }

  /**
   * Setting up the test object.
   */
  @Before
  public void initialize() {
    object = createEventFactory();
    EventReader.getInstance().readFromFile(Constants.EVENT_DATA_FILE);
  }

  /**
   * Test if Id increases with each event.
   */
  @Test
  public void testUniqueId() {
    Event e1 = object.createEvent();
    int id = e1.getId();
    Event e2 = object.createEvent();
    assertEquals(id + 1, e2.getId());
  }

}