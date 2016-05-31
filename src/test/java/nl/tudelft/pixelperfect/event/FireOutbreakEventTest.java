package nl.tudelft.pixelperfect.event;

import nl.tudelft.pixelperfect.event.type.FireOutbreakEvent;

/**
 * Tesing the FireEvent class.
 * 
 * @author Wouter Zirkzee
 * @author Floris Doolaard
 */
@SuppressWarnings("PMD")
public class FireOutbreakEventTest extends EventTest {

  /**
   * Factory method for testing.
   * 
   * @return PlasmaLeakEvent class
   */
  @Override
  public FireOutbreakEvent createEvent() {
    return new FireOutbreakEvent(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
  }

}