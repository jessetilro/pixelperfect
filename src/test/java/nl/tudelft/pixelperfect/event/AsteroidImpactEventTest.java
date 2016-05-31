package nl.tudelft.pixelperfect.event;

import nl.tudelft.pixelperfect.event.type.AsteroidImpactEvent;

/**
 * Tesing the AsteroidFieldEvent class.
 * 
 * @author Wouter Zirkzee
 * @author Floris Doolaard
 */
@SuppressWarnings("PMD")
public class AsteroidImpactEventTest extends EventTest {

  /**
   * Factory method for testing.
   * 
   * @return PlasmaLeakEvent class
   */
  @Override
  public AsteroidImpactEvent createEvent() {
    return new AsteroidImpactEvent(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
  }

}
