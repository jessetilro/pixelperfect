package nl.tudelft.pixelperfect.event.type;

import nl.tudelft.pixelperfect.event.EventTest;

/**
 * Testing the CoffeeBoostEvent class.
 * 
 * @author Jesse Tilro
 */
@SuppressWarnings("PMD")
public class CoffeeBoostEventTest extends EventTest {

  /**
   * Factory method for testing.
   * 
   * @return CoffeeBoostEvent instance.
   */
  @Override
  public CoffeeBoostEvent createEvent(String summary, String description) {
    return new CoffeeBoostEvent(1, summary, description, 42, 42, 99.42);
  }

}