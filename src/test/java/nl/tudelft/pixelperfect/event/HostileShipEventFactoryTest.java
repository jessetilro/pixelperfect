package nl.tudelft.pixelperfect.event;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.tudelft.pixelperfect.event.factory.EventFactory;
import nl.tudelft.pixelperfect.event.factory.HostileShipEventFactory;
import nl.tudelft.pixelperfect.event.type.Event;

/**
 * Test Suite for the HostileShipEventFactory class.
 * 
 * @author Jesse Tilro
 *
 */
public class HostileShipEventFactoryTest extends EventFactoryTest {

  /**
   * Create the test object.
   * 
   * @return The test object.
   */
  @Override
  public EventFactory createEventFactory() {
    return new HostileShipEventFactory();
  }

  /**
   * The Events created by this factory should have the right summary, read from the configuration
   * file.
   */
  @Test
  public void testIntegrationWithReader() {
    Event event = getObject().createEvent();
    assertEquals("Hostile Ship", event.getSummary());
  }

}
