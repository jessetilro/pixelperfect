package nl.tudelft.pixelperfect.event;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlasmaLeakEventFactoryTest extends EventFactoryTest {

  /**
   * Create the test object.
   * 
   * @return The test object.
   */
  @Override
  public EventFactory createEventFactory() {
    return new PlasmaLeakEventFactory();
  }

  /**
   * The Events created by this factory should have the right summary, read from the configuration
   * file.
   */
  @Test
  public void testIntegrationWithReader() {
    Event event = getObject().createEvent();
    assertEquals("Plasma Leak", event.getSummary());
  }

}
