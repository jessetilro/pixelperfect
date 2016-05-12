package nl.tudelft.pixelperfect.event;

/**
 * Created by woute on 5/10/2016.
 */
@SuppressWarnings("PMD")
public class HostileShipEventTest extends EventTest {

  /**
   * Factory method for testing.
   * 
   * @return PlasmaLeakEvent class
   */
  @Override
  public HostileShipEvent createEvent() {
    return new HostileShipEvent(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
  }

}
