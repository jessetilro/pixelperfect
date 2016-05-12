package nl.tudelft.pixelperfect.event;

/**
 * Created by woute on 5/10/2016.
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
