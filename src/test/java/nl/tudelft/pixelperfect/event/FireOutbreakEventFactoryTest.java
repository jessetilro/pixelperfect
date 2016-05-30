package nl.tudelft.pixelperfect.event;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FireOutbreakEventFactoryTest extends EventFactoryTest {

  /**
   * Create the test object.
   * 
   * @return The test object.
   */
  @Override
  public EventFactory createEventFactory() {
    return new FireOutbreakEventFactory();
  }

  /**
   * The Events created by this factory should have the right summary, read from the configuration
   * file.
   */
  @Test
  public void testIntegrationWithReader() {
    Event event = getObject().createEvent();
    assertEquals("Fire Outbreak", event.getSummary());
  }

}
