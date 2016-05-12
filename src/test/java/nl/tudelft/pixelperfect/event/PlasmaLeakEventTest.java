package nl.tudelft.pixelperfect.event;

/**
 * Created by woute on 5/10/2016.
 */
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
