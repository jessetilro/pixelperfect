package nl.tudelft.pixelperfect.event.type;

import nl.tudelft.pixelperfect.event.EventTest;

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
  public AsteroidImpactEvent createEvent(String summary, String description) {
    return new AsteroidImpactEvent(1, summary, description, 42, 42, 99.42);
  }

}
