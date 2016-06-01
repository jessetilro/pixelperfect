package nl.tudelft.pixelperfect.event.type;

import nl.tudelft.pixelperfect.event.EventTest;

/**
 * Tesing the HostileShipEvent class.
 * 
 * @author Wouter Zirkzee
 * @author Floris Doolaard
 */
@SuppressWarnings("PMD")
public class HostileShipEventTest extends EventTest {

  /**
   * Factory method for testing.
   * 
   * @return PlasmaLeakEvent class
   */
  @Override
  public HostileShipEvent createEvent(String summary, String description) {
    return new HostileShipEvent(1, summary, description, 42, 42, 99.42);
  }

}
