package nl.tudelft.pixelperfect.event;

/**
 * Tesing the AsteroidFieldEvent class.
 * 
 * @author Wouter Zirkzee
 * @author Floris Doolaard
 */
@SuppressWarnings("PMD")
public class AsteroidFieldEventTest extends EventTest {

  /**
   * Factory method for testing.
   * 
   * @return PlasmaLeakEvent class
   */
  @Override
  public AsteroidFieldEvent createEvent() {
    return new AsteroidFieldEvent(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
  }

}
