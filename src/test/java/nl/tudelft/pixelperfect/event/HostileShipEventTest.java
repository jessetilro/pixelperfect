package nl.tudelft.pixelperfect.event;

/**
 * Created by woute on 5/10/2016.
 */
public class HostileShipEventTest extends EventTest {

  /**
   * Factory method for testing.
   * @return HostileShipEvent class
   */
  public HostileShipEvent createEvent() {
      return new HostileShipEvent(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
    }

}
