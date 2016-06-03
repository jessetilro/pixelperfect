package nl.tudelft.pixelperfect.event.type;

import nl.tudelft.pixelperfect.event.EventTest;

/**
 * Tesing the PlasmaLeakEvent class.
 * 
 * @author Wouter Zirkzee
 * @author Floris Doolaard
 */
@SuppressWarnings("PMD")
public class PlasmaLeakEventTest extends EventTest {

  /**
   * Factory method for testing.
   * 
   * @return PlasmaLeakEvent class
   */
  @Override
  public PlasmaLeakEvent createEvent(String summary, String description) {
    return new PlasmaLeakEvent(1, summary, description, 42, 42, 99.42);
  }
}
