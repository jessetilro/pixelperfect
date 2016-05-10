package nl.tudelft.pixelperfect.event;

/**
 * Created by woute on 5/10/2016.
 */
public class HostileShipTest extends EventTest {

  /**
   * Factory method for testing.
   * @return HostileShip class
   */
  public HostileShip createEvent() {
      return new HostileShip(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
    }

}
