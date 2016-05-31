package nl.tudelft.pixelperfect.event;

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
  public CoffeeBoostEvent createEvent() {
    return new CoffeeBoostEvent(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
  }

}