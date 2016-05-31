package nl.tudelft.pixelperfect.event;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.tudelft.pixelperfect.event.factory.AsteroidImpactEventFactory;
import nl.tudelft.pixelperfect.event.factory.EventFactory;
import nl.tudelft.pixelperfect.event.type.Event;

/**
 * Test Suite for the AsteroidImpactEventFactory class.
 * 
 * @author Jesse Tilro
 *
 */
public class AsteroidImpactEventFactoryTest extends EventFactoryTest {

  /**
   * Create the test object.
   * 
   * @return The test object.
   */
  @Override
  public EventFactory createEventFactory() {
    return new AsteroidImpactEventFactory();
  }

  /**
   * The Events created by this factory should have the right summary, read from the configuration
   * file.
   */
  @Test
  public void testIntegrationWithReader() {
    Event event = getObject().createEvent();
    assertEquals("Asteroid Impact", event.getSummary());
  }

}
