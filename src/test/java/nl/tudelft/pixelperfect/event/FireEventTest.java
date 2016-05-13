package nl.tudelft.pixelperfect.event;

/**
 * Tesing the FireEvent class.
 * 
 * @author Wouter Zirkzee
 * @author Floris Doolaard
 */
@SuppressWarnings("PMD")
public class FireEventTest extends EventTest {

  /**
   * Factory method for testing.
   * 
   * @return PlasmaLeakEvent class
   */
  @Override
  public FireEvent createEvent() {
    return new FireEvent(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
  }

}