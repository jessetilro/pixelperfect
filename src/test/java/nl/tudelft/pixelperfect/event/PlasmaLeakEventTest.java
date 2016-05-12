package nl.tudelft.pixelperfect.event;

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
  public PlasmaLeakEvent createEvent() {
    return new PlasmaLeakEvent(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
  }
}
